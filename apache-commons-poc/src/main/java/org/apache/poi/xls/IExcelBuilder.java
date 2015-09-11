package org.apache.poi.xls;

import java.util.List;
import java.util.Map;

public interface IExcelBuilder<T> {

    /**
     * 添加title
     * 
     * @param title
     */
    public void addTitle(String title);

    /**
     * 添加列名
     * 
     * @param columnMap
     *            key->field name, value->column name
     */
    public void addColumn(Map<String, String> columnMap);

    /**
     * 添加所有列数据集
     * 
     * @param rowDatas
     */
    public void addRowData(List<T> rowDatas);

    /**
     * 添加生成路径及文件名
     * 
     * @param directory
     * @param fileName
     */
    public void addPath(String directory, String fileName);

    /**
     * 生产xls文件
     * 
     * @throws Exception
     */
    public void build() throws Exception;
}
