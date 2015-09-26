package com.example.mizhelibrary.service;

import com.example.mizhelibrary.model.Category;
import com.example.mizhelibrary.model.Goods;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.sauronsoftware.base64.Base64;

public class MiZheManager {

    public static MiZheManager m_instence;


    private MiZheManager() {
        super();
    }

    public static MiZheManager instence() {
        if (m_instence == null) {
            m_instence = new MiZheManager();
        }
        return m_instence;
    }

    public List<Category> getTeMaiCategory() throws Exception {
        List<Category> temai_list = new ArrayList<Category>();
        Document doc = Jsoup.connect("http://www.mizhe.com/").get();
        Elements elements = doc.getElementById("nav-menu").child(0).getElementsByTag("a");
        for (Element element : elements) {
            Category temai = new Category(element.attr("href"), element.text());
            temai_list.add(temai);

        }
        return temai_list;
    }

    public List<Category> getSubCategory(Category category) {
        List<Category> result_list = new ArrayList<Category>();

        Document doc = getUrl(category.getUrl());
        Elements cates = doc.getElementsByClass("sub-menu-wrap").get(0).getElementsByTag("a");
        for (Element element : cates) {
            result_list.add(new Category(element.attr("href"), element.text()));
        }

        return result_list;
    }

    private Document getUrl(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Goods> get_all_goods_list() {
        List<Goods> result = new ArrayList<>();
        Document doc = getUrl("http://www.mizhe.com/");
        System.out.println("jsoup:"+"http://www.mizhe.com/");
        Elements elements = doc.getElementsByClass("shop-wrap").get(0).child(1).getElementsByTag("a");
        for (Element element : elements) {
            String image_url = Base64.decode(element.getElementsByClass("promo-img").get(0).child(0).attr("data-src"));
            image_url = fix_image_url(image_url);
            Goods goods = new Goods(element.attr("href"),
                    image_url,
                    element.getElementsByClass("intro-text").get(0).text(),
                    element.getElementsByClass("item-price-info").get(0).text(),
                    element.getElementsByClass("org-price").get(0).text(),
                    element.getElementsByClass("people-num").get(0).text());
            result.add(goods);
        }
        return result;
    }

    public List<Goods> get_goods_by_category(Category category, int page) {
        List<Goods> result = new ArrayList<>();
        if (category.getName().equals("全部")) {
            return get_all_goods_list();
        }
        String url = category.getUrl();
        if (url.contains("l_9kuai9")) {//9.9包邮
            url = String.format("http://tuan.mizhe.com/l_9kuai9/all/recom/1/%s.html?infinite=true", page);
        } else {
            url = url.substring(0, category.getUrl().lastIndexOf(".")) + "/1/" + page + ".html";
        }

        System.out.println("jsoup:"+category.getUrl().substring(0, category.getUrl().lastIndexOf(".")) + "/1/" + page + ".html");
        Document doc = getUrl(url);
        if (doc == null) {
            return null;
        }
        Elements elements = doc.select("div.item-list.infinite-scroll").get(0).getElementsByTag("a");

        for (Element element : elements) {
            try {
                String image_url = Base64.decode(element.getElementsByClass("promo-img").get(0).child(0).attr("data-src"));
                image_url = fix_image_url(image_url);
                Goods goods = new Goods(element.attr("href"),
                        image_url,
                        element.getElementsByClass("intro-text").get(0).text(),
                        element.getElementsByClass("item-price-info").get(0).text(),
                        element.getElementsByClass("org-price").get(0).text(),
                        element.getElementsByClass("people-num").get(0).text());
                //            System.out.println(goods.toString());
                result.add(goods);
            } catch (RuntimeException e) {
                e.printStackTrace();
                continue;
            }
        }

        return result;
    }

    private String fix_image_url(String image_url) {
        if (image_url.endsWith(".j") || image_url.endsWith(".jp")) {
            image_url = image_url.substring(0, image_url.lastIndexOf(".j")) + ".jpg";
        } else if (image_url.endsWith("00")) {
            image_url = image_url.substring(0, image_url.lastIndexOf(".jpg"));
        }
        if (!image_url.startsWith("http")) {
            image_url = "http://" + image_url;
        }
        return image_url;
    }


}
