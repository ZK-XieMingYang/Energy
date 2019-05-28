package com.qhit.baseDevtype.controller; 

import com.qhit.baseDevtype.pojo.BaseDevtype; 
import com.qhit.baseDevtype.service.IBaseDevtypeService;
import com.qhit.utils.ExceldUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.springframework.web.bind.annotation.RestController; 



@RestController 
@RequestMapping("/baseDevtype") 
public class BaseDevtypeController { 

    @Resource 
    IBaseDevtypeService baseDevtypeService; 

    @RequestMapping("/insert") 
    public void insert(BaseDevtype baseDevtype) { 
        baseDevtypeService.insert(baseDevtype); 
    } 

    @RequestMapping("/delete") 
    public void delete(Integer typeid) { 
        baseDevtypeService.delete(typeid); 
    } 

    @RequestMapping("/update") 
    public void update(BaseDevtype baseDevtype) { 
        baseDevtypeService.update(baseDevtype); 
    } 

    @RequestMapping("/updateSelective") 
    public void updateSelective(BaseDevtype baseDevtype) { 
        baseDevtypeService.updateSelective(baseDevtype); 
    } 

    @RequestMapping("/load") 
    public BaseDevtype load(Integer typeid) { 
        BaseDevtype baseDevtype = baseDevtypeService.findById(typeid); 
        return baseDevtype; 
    } 

    @RequestMapping("/list") 
    public List<BaseDevtype> list( HttpServletResponse response)  {
        List<BaseDevtype> list = baseDevtypeService.findAll();
        try {
            ExceldUtil.createExcel(response,list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list; 
    } 

    @RequestMapping("/search") 
    public List<BaseDevtype> search(BaseDevtype baseDevtype) { 
        List<BaseDevtype> list = baseDevtypeService.search(baseDevtype); 
        return list; 
    }
    @RequestMapping("/outExcle")
    public void  out(HttpServletResponse response) throws IOException {
        List<BaseDevtype> list = baseDevtypeService.findAll();
        HSSFWorkbook wb = new HSSFWorkbook();
//建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("机械类别表");
//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);
//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("机械类别一览表");
//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
//在sheet里创建第二行
        HSSFRow row2=sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("类别ID");
        row2.createCell(1).setCellValue("名称");
        //在sheet里创建第三行
        int i=2;
        for(BaseDevtype item:list){
            HSSFRow rownext=sheet.createRow(i);
            rownext.createCell(0).setCellValue(item.getTypeid());
            rownext.createCell(1).setCellValue(item.getTypename());
            i++;
        }


//输出Excel文件
        OutputStream output=response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=details.xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
    }
} 
