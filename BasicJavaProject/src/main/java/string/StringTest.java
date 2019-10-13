package string;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author Pjq
 * @Date 2019/10/6
 **/
public class StringTest {

    public static void main(String[] args) {
        printList();
    }

    public static void printList(){
        List<TestObject> list = sortList();
        list.stream().forEach(e -> System.out.println(e.getRoleName()));
    }

    /**
     * 公司的一个项目（CRM）需求需要进行排序，排序有可能会改变。
     * 根据排序规则进行排序
     * @return
     */
    public static List<TestObject> sortList() {
        Map<String, String> map = initRuleMapData();
        List<TestObject> result = new ArrayList();
        List<TestObject> list = initListData();
        map.forEach((k,v) ->{
            list.stream().forEach(e ->{
                TestObject testObject = e;
                if (v.equals(testObject.getRoleCd())) {
                    result.add(testObject);
                }
            });
        });
        list.removeAll(result);
        result.addAll(list);
        return result;
    }

    /**
     * 配置排序规则
     * @return
     */
    public static Map initRuleMapData(){
        Map<String,String> map = new TreeMap<>();
        map.put("1","10100002");
        map.put("2","20100002");
        map.put("3","10100001");
        map.put("4","10200001");
        map.put("5","10300001");
        return map;
    }
    /**
     * 初始化数据
     * @return
     */
    public static List<TestObject> initListData(){
        List<TestObject> list = new ArrayList();
        list.add(new TestObject("10300001","基础ITV"));
        list.add(new TestObject("10100002","基础移动电话"));
        list.add(new TestObject("10200001","基础有线宽带"));
        list.add(new TestObject("10100001","基础固话"));
        list.add(new TestObject("20100002","加装移动电话"));
        list.add(new TestObject("20100001","加装固话"));
        list.add(new TestObject("20200001","加装有线宽带"));
        list.add(new TestObject("20300001","加装ITV"));
        return list;
    }


}

class TestObject{

    public String roleCd;
    public String roleName;

    TestObject(String roleCd,String roleName){
       this.roleCd = roleCd;
       this.roleName = roleName;
    }
    public String getRoleCd() {
        return roleCd;
    }

    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}