package com.spring.testautomation.page.google;

import com.spring.testautomation.driver.annotations.Page;
import com.spring.testautomation.page.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author cristian_iosef
 */
@Page
public class GooglePage extends Base{

    @Autowired
    private SearchComponent searchComponent;

    @Autowired SearchResult searchResult;

    @Value("${application.url}")
    private String url;

    public SearchComponent getSearchComponent() {
        return searchComponent;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }


    public void goTo(){
        this.driver.get(url);
        searchComponent.acceptCookies();
    }
    @Override
    public boolean isAt() {
        return this.searchComponent.isAt();
    }
}
