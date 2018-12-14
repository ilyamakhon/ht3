package com.epam.ht3.dataprovider;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviderClass {

    @DataProvider
    public Object[][] dataProvider(Method method) {
        switch (method.getName()) {
            case "tst_repositorySearch_Via":
                return new Object[][]{
                        {"headerNavSearchInput"},
                        { "searchingSidebar" },
                        { "repositoriesPage" }
                };
            case "tst_repositoryDeletion":
                return new Object[][]{
                        { "searchingSidebar" }
                };
        }
        return new Object[][]{};
    }
}
