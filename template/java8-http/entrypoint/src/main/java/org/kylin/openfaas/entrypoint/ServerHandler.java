package org.kylin.openfaas.entrypoint;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.kylin.openfaas.function.Handler;
import org.kylin.openfaas.model.*;

import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderValues.CLOSE;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;


public class ServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) {
        String body = request.content().toString(CharsetUtil.UTF_8);
        IRequest iRequest = new Request(body, convertHeader(request.headers()), request.uri());
        IResponse iResponse = new Handler().handle(iRequest);

        boolean keepAlive = isKeepAlive(request);
        FullHttpResponse response = new DefaultFullHttpResponse(request.protocolVersion(),
                HttpResponseStatus.valueOf(iResponse.getStatusCode()),
                Unpooled.wrappedBuffer(iResponse.getBody().getBytes()));

        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        Map<String, List<String>> headerMap = iResponse.getHeaders().entries();
        if (null != headerMap && headerMap.size() > 0) {
            headerMap.entrySet().forEach(e -> e.getValue().forEach(
                    v -> response.headers().set(e.getKey(), v)
                    )
            );
        }

        if (keepAlive) {
            if (!request.protocolVersion().isKeepAliveDefault()) {
                response.headers().set(CONNECTION, KEEP_ALIVE);
            }
        } else {
            // Tell the client we're going to close the connection.
            response.headers().set(CONNECTION, CLOSE);
        }

        ChannelFuture f = ctx.write(response);

        if (!keepAlive) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    private Header convertHeader(HttpHeaders headers) {
        Header Header = new Header();
        headers.entries().forEach(e -> Header.setHeader(e.getKey(), e.getValue()));
        return Header;
    }
}
