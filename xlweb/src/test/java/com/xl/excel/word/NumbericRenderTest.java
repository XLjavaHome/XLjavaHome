package com.xl.excel.word;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.NumbericRenderData;
import static com.deepoove.poi.data.NumbericRenderData.*;
import com.deepoove.poi.data.TextRenderData;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat.Enum;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-03-24
 * @time 21:18
 * To change this template use File | Settings | File Templates.
 */
public class NumbericRenderTest {
    @SuppressWarnings("serial")
    @Test
    public void testNumbericRender() throws Exception {
        Map<String, Object> datas = new HashMap<String, Object>() {
            {
                //1. 2. 3.
                put("number123", getData(FMT_DECIMAL));
                put("number123_dulplicate", getData(FMT_DECIMAL));
                //1) 2) 3)
                put("number123_kuohao", getData(FMT_DECIMAL_PARENTHESES));
                //无序
                put("bullet", getData(FMT_BULLET));
                //A B C
                put("ABC", getData(FMT_UPPER_LETTER));
                //a b c
                put("abc", getData(FMT_LOWER_LETTER));
                //ⅰ ⅱ ⅲ
                put("iiiiii", getData(FMT_LOWER_ROMAN));
                //Ⅰ Ⅱ Ⅲ
                put("IIIII", getData(FMT_UPPER_ROMAN));
                //自定义有序列表显示 (one) (two) (three)
                put("custom_number", getData(Pair.of(STNumberFormat.CARDINAL_TEXT, "(%1)")));
                //自定义无序列表显示：定义无序符号
                put("custom_bullet", getData(Pair.of(STNumberFormat.BULLET, "♬")));
            }
        };
        XWPFTemplate template = XWPFTemplate.compile("src/test/resources/numberic.docx").render(datas);
        FileOutputStream out = new FileOutputStream("out_numberic.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }
    
    @SuppressWarnings("serial")
    private NumbericRenderData getData(Pair<Enum, String> pair) {
        return new NumbericRenderData(pair, new ArrayList<TextRenderData>() {
            {
                add(new TextRenderData("df2d4f", "Deeply in love with the things you love, just deepoove."));
                add(new TextRenderData("Deeply in love with the things you love, just deepoove."));
                add(new TextRenderData("5285c5", "Deeply in love with the things you love, just deepoove."));
            }
        });
    }
}
