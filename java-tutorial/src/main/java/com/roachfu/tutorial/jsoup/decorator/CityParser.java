package com.roachfu.tutorial.jsoup.decorator;

import com.roachfu.tutorial.jsoup.Node;
import com.roachfu.tutorial.util.HttpClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

/**
 * @author roach
 * @date 2018/8/12 16:45
 */
public class CityParser implements ICityParser {

    private static final String COMMON_URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/";

    @Override
    public List<Node> parseProvinces(String url) {
        return parseProvince(COMMON_URL + "index.html");
    }

    /**
     * 解析的数据如下
     *
     *  <tr class='provincetr'>
     *      <td><a href='11.html'>北京市<br/></a></td>
     *      <td><a href='12.html'>天津市<br/></a></td>
     *  </tr>
     *  <tr class='provincetr'>
     *      <td><a href='31.html'>上海市<br/></a></td>
     *      <td><a href='32.html'>江苏省<br/></a></td>
     *  </tr>
     *
     * @param url 请求url
     */
    private List<Node> parseProvince(String url) {

        String htmlStr = HttpClientUtils.httpGetRequest(url, "GBK");
        Document document = Jsoup.parse(htmlStr);

        // 获取 class='provincetr' 的元素
        Elements elements = document.getElementsByClass("provincetr");
        List<Node> provinces = new LinkedList<>();
        for (Element element : elements) {
            // 获取 elements 下属性是 href 的元素
            Elements links = element.getElementsByAttribute("href");
            for (Element link : links) {
                String provinceName = link.text();
                String href = link.attr("href");
                String provinceCode = href.substring(0, 2);
                System.out.println("provinceName: " + provinceName + ", provinceCode: " + provinceCode);

                Node provinceNode = Node.builder()
                        .code(provinceCode)
                        .name(provinceName)
                        .nodes(parseCity(COMMON_URL + href))
                        .build();
                provinces.add(provinceNode);
            }
        }
        return provinces;
    }

    /**
     *  <tr class='citytr'>
     *      <td><a href='36/3601.html'>360100000000</a></td>
     *      <td><a href='36/3601.html'>南昌市</a></td>
     *  </tr>
     *  <tr class='citytr'>
     *      <td><a href='36/3602.html'>360200000000</a></td>
     *      <td><a href='36/3602.html'>景德镇市</a></td>
     * </tr>
     *
     * @param url 请求url
     */
    private List<Node> parseCity(String url) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String htmlStr = HttpClientUtils.httpGetRequest(url, "GBK");
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("citytr");

        List<Node> cities = new LinkedList<>();
        for (Element tr : trs) {
            Elements links = tr.getElementsByTag("a");
            String href = links.get(0).attr("href");
            String cityCode = links.get(0).text().substring(0, 4);
            String cityName = links.get(1).text();
            System.out.println("    cityName: " + cityName + ", cityCode: " + cityCode);

            Node cityNode = Node.builder()
                    .name(cityName)
                    .code(cityCode)
//                    .nodes(parseCounty(COMMON_URL + href))
                    .build();
            cities.add(cityNode);
        }
        return cities;
    }

    /**
     *  <tr class='countytr'>
     *      <td>361101000000</td>
     *      <td>市辖区</td>
     *  </tr>
     *  <tr class='countytr'>
     *      <td><a href='11/361102.html'>361102000000</a></td>
     *      <td><a href='11/361102.html'>信州区</a></td>
     *  </tr>
     *  <tr class='countytr'>
     *      <td><a href='11/361103.html'>361103000000</a></td>
     *      <td><a href='11/361103.html'>广丰区</a></td>
     *  </tr>
     *
     * @param url 请求url
     */
    private List<Node> parseCounty(String url) {
        String htmlStr = HttpClientUtils.httpGetRequest(url, "GBK");
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("countytr");

        List<Node> counties = new LinkedList<>();
        for (Element tr : trs) {
            Elements links = tr.getElementsByTag("a");
            if (links == null || links.size() != 2) {
                continue;
            }
            String countyCode = links.get(0).text().substring(0, 6);
            String countyName = links.get(1).text();
            System.out.println("        countyName: " + countyName + ", countyCode: " + countyCode);

            Node node = Node.builder()
                    .code(countyCode)
                    .name(countyName)
                    .build();
            counties.add(node);
        }
        return counties;
    }
}
