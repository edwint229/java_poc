package test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

public class DateDescCheck {

    @Test
    public void dataAscCheck() {
        String json = "{\"content\":{\"totalRecords\":415,\"records\":[{\"amount\":200,\"buyDate\":1414406592000,\"username\":\"d***0\"},{\"amount\":100,\"buyDate\":1414406706000,\"username\":\"d***b\"},{\"amount\":2000,\"buyDate\":1414406997000,\"username\":\"d***1\"},{\"amount\":10000,\"buyDate\":1414407008000,\"username\":\"d***y\"},{\"amount\":1000,\"buyDate\":1414407215000,\"username\":\"d***e\"},{\"amount\":1000,\"buyDate\":1414407427000,\"username\":\"d***c\"},{\"amount\":200,\"buyDate\":1414407657000,\"username\":\"d***9\"},{\"amount\":100,\"buyDate\":1414407963000,\"username\":\"d***5\"},{\"amount\":5000,\"buyDate\":1414408084000,\"username\":\"d***l\"},{\"amount\":1000,\"buyDate\":1414408152000,\"username\":\"d***4\"},{\"amount\":100,\"buyDate\":1414408235000,\"username\":\"d***f\"},{\"amount\":100,\"buyDate\":1414408253000,\"username\":\"d***8\"},{\"amount\":2000,\"buyDate\":1414408360000,\"username\":\"d***b\"},{\"amount\":2000,\"buyDate\":1414408368000,\"username\":\"d***b\"},{\"amount\":2000,\"buyDate\":1414408375000,\"username\":\"d***b\"},{\"amount\":2000,\"buyDate\":1414408385000,\"username\":\"d***b\"},{\"amount\":2000,\"buyDate\":1414408394000,\"username\":\"d***b\"},{\"amount\":2000,\"buyDate\":1414408515000,\"username\":\"d***b\"},{\"amount\":2000,\"buyDate\":1414408525000,\"username\":\"d***b\"},{\"amount\":2000,\"buyDate\":1414408535000,\"username\":\"d***b\"}],\"summaryData\":{\"amount\":36800}},\"result\":\"success\",\"errors\":[]}";

        Gson gson = new Gson();
        Map jsonMap = gson.fromJson(json, Map.class);
        Map contentMap = (Map) jsonMap.get("content");
        List recordList = (List) contentMap.get("records");
        Double summaryAmount = (Double) ((Map) contentMap.get("summaryData")).get("amount");
        Double totalAmount = 0d;

        Long tmpBuyDateMilliSec = this.getBuyDate(recordList.get(0));
        for (int i = 1; i < recordList.size(); i++) {
            Object record = recordList.get(i);
            Long nextBuyDateMilliSec = this.getBuyDate(record);
            totalAmount += this.getAmount(record);
            
            // 判断日期升序排序是否正确
            if (nextBuyDateMilliSec < tmpBuyDateMilliSec) {
                Assert.fail(String.format("Found Buy Date Asc Order Error, Current Records %s", record));
                break;
            }

            tmpBuyDateMilliSec = nextBuyDateMilliSec;
        }
        
        // 判断总金额是否正确
        Assert.assertEquals(summaryAmount, totalAmount);

    }

    private Long getBuyDate(Object record) {
        Map recordMap = (Map) record;
        Long buyDateMilliSec = ((Double) recordMap.get("buyDate")).longValue();
        System.out.println(new Date(buyDateMilliSec));
        return buyDateMilliSec;
    }

    private Double getAmount(Object record) {
        Map recordMap = (Map) record;
        return (Double) recordMap.get("amount");
    }

}
