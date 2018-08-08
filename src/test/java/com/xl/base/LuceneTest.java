package com.xl.base;

import com.xl.entity.User;
import com.xl.util.LuceneUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-03-20
 * @Time: 13:41
 * To change this template use File | Settings | File Templates.
 */
public class LuceneTest {
    /**
     * 创建索引库的步骤：
     * <p>
     * 1）创建JavaBean对象
     * 2）创建Docment对象
     * 3）将JavaBean对象所有的属性值，均放到Document对象中去，属性名可以和JavaBean相同或不同
     * 4）创建IndexWriter对象
     * 5）将Document对象通过IndexWriter对象写入索引库中
     * 6）关闭IndexWriter对象
     *
     * @throws Exception
     */
    @Test
    public void createIndexDB() throws Exception {
        //把数据填充到JavaBean对象中
        User user = new User();
        user.setId(1);
        user.setUserName("钟福成");
        user.setSal("未来的程序员");
        //创建Document对象【导入的是Lucene包下的Document对象】
        Document document = new Document();
        //将JavaBean对象所有的属性值，均放到Document对象中去，属性名可以和JavaBean相同或不同
         /*
         * 向Document对象加入一个字段
         * 参数一：字段的关键字
         * 参数二：字符的值
         * 参数三：是否要存储到原始记录表中
         *      YES表示是
         *      NO表示否
         * 参数四：是否需要将存储的数据拆分到词汇表中
         *      ANALYZED表示拆分
         *      NOT_ANALYZED表示不拆分
         *
         */
        document.add(new Field("id", user.getId() + "", Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("userName", user.getUserName(), Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("sal", user.getSal(), Field.Store.YES, Field.Index.ANALYZED));
        //创建IndexWriter对象
        //目录指定为E:/createIndexDB
        Directory directory = FSDirectory.open(getLucenceDir());
        //使用标准的分词算法对原始记录表进行拆分
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
        //LIMITED默认是1W个
        IndexWriter.MaxFieldLength maxFieldLength = IndexWriter.MaxFieldLength.LIMITED;
            /*
         * IndexWriter将我们的document对象写到硬盘中
         *
         * 参数一：Directory d,写到硬盘中的目录路径是什么
         * 参数二：Analyzer a, 以何种算法来对document中的原始记录表数据进行拆分成词汇表
         * 参数三：MaxFieldLength mfl 最多将文本拆分出多少个词汇
         *
         */
        IndexWriter indexWriter = new IndexWriter(directory, analyzer, maxFieldLength);
        //将Document对象通过IndexWriter对象写入索引库中
        indexWriter.addDocument(document);
        //关闭IndexWriter对象
        indexWriter.close();
    }

    @NotNull
    private File getLucenceDir() {
        return new File("E:/createIndexDB");
    }

    /**
     * 根据关键字查询索引库中的内容：
     * <p>
     * 1）创建IndexSearcher对象
     * 2）创建QueryParser对象
     * 3）创建Query对象来封装关键字
     * 4）用IndexSearcher对象去索引库中查询符合条件的前100条记录，不足100条记录的以实际为准
     * 5）获取符合条件的编号
     * 6）用indexSearcher对象去索引库中查询编号对应的Document对象
     * 7）将Document对象中的所有属性取出，再封装回JavaBean对象中去，并加入到集合中保存，以备将之用
     */
    @Test
    public void findIndexDB() throws Exception {
        /**
         * 参数一： IndexSearcher(Directory PATH)查询以xxx目录的索引库
         *
         * */
        Directory directory = FSDirectory.open(getLucenceDir());
        //创建IndexSearcher对象
        IndexSearcher indexSearcher = new IndexSearcher(directory);
        //创建QueryParser对象
        /**
         * 参数一： Version matchVersion 版本号【和上面是一样的】
         * 参数二：String f,【要查询的字段】
         * 参数三：Analyzer a【使用的拆词算法】
         * */
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
        QueryParser queryParser = new QueryParser(Version.LUCENE_30, "userName", analyzer);
        //给出要查询的关键字
        String keyWords = "钟";
        //创建Query对象来封装关键字
        Query query = queryParser.parse(keyWords);
        //用IndexSearcher对象去索引库中查询符合条件的前100条记录，不足100条记录的以实际为准
        TopDocs topDocs = indexSearcher.search(query, 100);
        //获取符合条件的编号
        for (int i = 0; i < topDocs.scoreDocs.length; i++) {
            ScoreDoc scoreDoc = topDocs.scoreDocs[i];
            int no = scoreDoc.doc;
            //用indexSearcher对象去索引库中查询编号对应的Document对象
            Document document = indexSearcher.doc(no);
            //将Document对象中的所有属性取出，再封装回JavaBean对象中去
            String id = document.get("id");
            String userName = document.get("userName");
            String sal = document.get("sal");
            User user = new User();
            user.setName(id);
            user.setUserName(userName);
            user.setSal(sal);
            System.out.println(user);
        }
    }

    @Test
    public void lightTest() throws IOException, InvalidTokenOffsetsException, ParseException {
        String keywords = "钟福成";
        List<User> UserList = new ArrayList<User>();
        QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(), "content", LuceneUtil.getAnalyzer());
        Query query = queryParser.parse(keywords);
        IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
        TopDocs topDocs = indexSearcher.search(query, 1000000);
        //设置关键字高亮
        Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
        Scorer scorer = new QueryScorer(query);
        Highlighter highlighter = new Highlighter(formatter, scorer);
        for (int i = 0; i < topDocs.scoreDocs.length; i++) {
            ScoreDoc scoreDoc = topDocs.scoreDocs[i];
            int no = scoreDoc.doc;
            Document document = indexSearcher.doc(no);
            //设置内容高亮
            String highlighterContent = highlighter.getBestFragment(LuceneUtil.getAnalyzer(), "content", document.get("content"));
            document.getField("content").setValue(highlighterContent);
            User User = (User) LuceneUtil.Document2JavaBean(document, User.class);
            UserList.add(User);
        }
        for (User User : UserList) {
            System.out.println(User);
        }
    }

    @Test
    public void test() {
        User user = new User();
        LuceneUtil.javaBean2Document(user);
    }
}

