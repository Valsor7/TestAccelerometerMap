package com.boost.testaccelerometermap.domain.response;


import java.util.List;

public class Response<T> {
    public List<T> dataList;

    public Response(List<T> ts) {
        dataList.addAll(ts);
    }
}
