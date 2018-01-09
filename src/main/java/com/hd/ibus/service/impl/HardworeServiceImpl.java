package com.hd.ibus.service.impl;

import com.hd.ibus.pojo.SessionData;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.HardworeService;
import com.hd.ibus.socketserver.GlobalSessionData;
import com.hd.ibus.socketserver.MinaIOHandler;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.shenw.PageHelp;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ThinkPad on 2017-07-31.
 */
@Service
public class HardworeServiceImpl implements HardworeService {
    public DataGridResultInfo hardworeList(PageHelp pageHelp,Integer pageNow) {

        Map<Long,SessionData> map = GlobalSessionData.getSessionDataMap();

        PageBean pageBean = pageHelp.getPageBean();
        int pageSize = map.size()/10+1;

//        pageBean.getStartRow(pageNow,pageSize);
//        pageHelp.setPageBean(pageBean);

        List<SessionData> sessionDatas = new ArrayList<SessionData>(map.values());
        Integer total = map.size();

        DataGridResultInfo dataGridResultInfo = new DataGridResultInfo(total,sessionDatas);
        dataGridResultInfo.setPageNow(pageNow);
        return dataGridResultInfo;
    }

    public void order(String dtuId, String orderStr) {
        IoSession session = GlobalSessionData.getSessionMap().get(dtuId);
        session.write(orderStr);
    }
}
