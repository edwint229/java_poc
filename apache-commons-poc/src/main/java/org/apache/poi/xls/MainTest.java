package org.apache.poi.xls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainTest {

    public static void main(String[] args) throws Exception {
        List<PayAmount> rowDatas = new ArrayList<PayAmount>();
        rowDatas.add(new PayAmount("唐金盛", "32323451234121234", "某某破", "133333232323232", new BigDecimal("8999.03")));
        rowDatas.add(new PayAmount("唐金盛1", "32323451234121234", "某某破1", "133333232323232", new BigDecimal("1999.03")));
        rowDatas.add(new PayAmount("唐金盛2", "32323451234121234", "某某破2", "133333232323232", new BigDecimal("2999.03")));
        rowDatas.add(new PayAmount("唐金盛3", "32323451234121234", "某某破3", "133333232323232", new BigDecimal("3999.03")));
        rowDatas.add(new PayAmount("唐金盛4", "32323451234121234", "某某破4", "133333232323232", new BigDecimal("4999.03")));
        rowDatas.add(new PayAmount("唐金盛5", "32323451234121234", "某某破5", "133333232323232", new BigDecimal("5999.03")));
        rowDatas.add(new PayAmount("唐金盛6", "32323451234121234", "某某破6", "133333232323232", new BigDecimal("6999.03")));

        XlsBuilder<PayAmount> builder = new XlsBuilder<PayAmount>();
        builder.addTitle("某某破某某破5");
        
        
        Map<String, String> columnMap = new LinkedHashMap<String, String>();
        columnMap.put("payerName", "付款人");
        columnMap.put("payerAcct", "付款人账号");
        columnMap.put("payeeName", "收款人");
        columnMap.put("payeeAcct", "收款人账号");
        columnMap.put("amount", "金额");
        
        builder.addColumn(columnMap);
        builder.addRowData(rowDatas);
        builder.addPath("D:/tmp", "付款方报表.xls");
        builder.build(); 
    }
}
