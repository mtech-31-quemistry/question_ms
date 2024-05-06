package com.mtech.qmsquestion.config;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.times;

public class RequestResponseFilterTest {
    private final RequestResponseFilter requestResponseFilter = Mockito.spy(new RequestResponseFilter());

    @Test
    public void testDoFilter() throws IOException, ServletException {

        MockHttpServletRequest req = new MockHttpServletRequest();
        req.addHeader("test", "test");


        req.setContent("hello".getBytes());
        MockHttpServletResponse res = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();
        requestResponseFilter.doFilter(req, res, chain);
        Mockito.verify(requestResponseFilter, times(1)).doFilter(Mockito.eq(req), Mockito.eq(res), Mockito.eq(chain));
    }

    @Test
    public void testDoFilter_ThrowsIOException() throws IOException, ServletException {

        MockHttpServletRequest req = new MockHttpServletRequest();
        req.addHeader("test", "test");


        req.setContent("hello".getBytes());
        MockHttpServletResponse res = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();
        requestResponseFilter.doFilter(req, res, chain);
        Mockito.verify(requestResponseFilter, times(1)).doFilter(Mockito.eq(req), Mockito.eq(res), Mockito.eq(chain));
    }
}
