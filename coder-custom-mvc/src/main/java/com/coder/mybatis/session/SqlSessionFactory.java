package com.coder.mybatis.session;

import com.coder.mybatis.config.Configuration;
import com.coder.mybatis.config.MappedSatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * 数据库SQL工厂
 * 1.把配置文件加载到内存
 * 2.工厂类生产sqlsession
 */
public class SqlSessionFactory {

    private Configuration configuration = new Configuration();

    public SqlSessionFactory(){
        //加载数据库文件配置信息
        loadDbInfo();
        //把Mapper配置加载到内存中去
        loadMapperInfo();
    }

    //记录mapper xml文件存放的位置
    public static final String MAPPER_CONFIG_LOCATION = "mapper";
    //记录数据库连接信息文件存放位置
    public static final String DB_CONFIG_FILE = "db.properties";

    //加载数据库配置信息
    private void loadDbInfo(){
        //加载数据库信息配置文件
        InputStream dbIn = SqlSessionFactory.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE);
        Properties properties = new Properties();
        try {
            properties.load(dbIn); //将配置文件写入properties对象
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将数据库配置信息写入configuration对象中
        configuration.setJdbcDriver(properties.get("jdbc.driver").toString());
        configuration.setJdbcUrl(properties.get("jdbc.url").toString());
        configuration.setUserName(properties.get("jdbc.username").toString());
        configuration.setUserPassword(properties.get("jdbc.password").toString());
    }

    //遍历指定文件夹下的所有mapper.xml
    private void loadMapperInfo(){
        URL resource = null;
        resource = SqlSessionFactory.class.getClassLoader().getResource(MAPPER_CONFIG_LOCATION);
        File mappers = new File(resource.getFile()); //获取指定的文件夹信息
        if(mappers.isDirectory()){
            File[] listFiles = mappers.listFiles();
            //遍历文件夹下所有的mapper.xml，解析信息后，注册至configuration对象中
            for (File file:listFiles){
                loadMapperFileInfo(file);
            }
        }
    }

    //加载指定的mapper.xml文件
    private void loadMapperFileInfo(File file){
        //创建saxReader对象 (依赖dom4j.jar)
        SAXReader reader = new SAXReader();
        //通过read方法读取一个文件 转换成Document对象
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //获取根节点元素对象<mapper>
        Element root = document.getRootElement();
        //获取命名空间
        String namespace = root.attribute("namespace").getData().toString();
        //获取select子节点列表
        List<Element> selects = root.elements("select");
        //遍历select节点，将信息记录到MappedStatement对象，并登记到configuration对象中
        for (Element element:selects){
            //实例化mappedStatement
            MappedSatement mappedSatement = new MappedSatement();
            //读取id属性
            String id = element.attribute("id").getData().toString();
            //读取resultType属性
            String resultType = element.attribute("resultType").getData().toString();
            //读取SQL语句信息
            String sql = element.getData().toString();
            String sourceId = namespace+"."+id;
            //给mappedStatement赋值
            mappedSatement.setSourceId(sourceId);
            mappedSatement.setResultType(resultType);
            mappedSatement.setSql(sql);
            mappedSatement.setNamespace(namespace);
            //注册到configuration对象中
            configuration.getMappedSatements().put(sourceId,mappedSatement);
        }
    }

    //生产SqlSession
    public SqlSession openSession(){
        return new DefaultSqlSession(configuration);
    }

}
