package techcr.learn.microservice.zuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.protocol.RequestContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoginFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(ZuulLoginFilter.class);

    /*
    when filter should happen, pre / post / error
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * Order of filters.
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /*
    should filter request from this filter. can implement logic here
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * logic here, what should be done inside filter.
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info("request {} - request uri - {}", request, request.getRequestURI());
        return null;
    }
}
