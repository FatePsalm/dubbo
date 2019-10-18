package com.wemew.wmlog.service;

public interface LogCustomAnalysisService {
    void writeLog(Integer category, Integer type, String userid, String username, String moreinfo, Integer price,
                  String barid, String barname);


}
