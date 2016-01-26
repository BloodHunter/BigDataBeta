package com.wbl.modal.exception;

/**
 * Created withSimple_love
 * Date: 2016/1/8.
 * Time: 9:47
 *
 * Catch exception when make a Http request
 */
public class RequestException extends Exception {
        public RequestException() {
        }

        public RequestException(String message) {
                super(message);
        }
}
