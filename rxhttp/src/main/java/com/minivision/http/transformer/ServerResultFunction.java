package com.minivision.http.transformer;

import com.minivision.http.bean.BaseHttpResult;
import com.minivision.http.bean.ResMsg;
import com.minivision.http.exception.ErrorType;
import com.minivision.http.exception.ServerException;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import java.util.List;

/**
 * 服务器错误信息过滤
 *
 * @author gaofeng
 * @date 2019/7/30
 */
public class ServerResultFunction<T> implements Function<BaseHttpResult<T>, T> {
    @Override
    public T apply(@NonNull BaseHttpResult<T> response) throws Exception {
        /*
         *  "resCode": 1,
         * "resData": {},
         * "resMsg": [{
         * "msgCode": "11111",
         * "msgText": "11111"
         * }]
         * code！=1时，过滤错误信息
         */
        if (response.getResCode() != ErrorType.SUCCESS) {
            List<ResMsg> resMsg = response.getResMsg();
            if (resMsg != null && resMsg.size() > 0) {
                throw new ServerException(Integer.valueOf(resMsg.get(0).getMsgCode()), resMsg.get(0).getMsgText());
            }
        }
        return response.getResData();
    }
}
