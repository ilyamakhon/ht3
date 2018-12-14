package com.epam.ht3.dataprovider;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviderClass {

    @DataProvider
    public Object[][] dataProvider(Method method) {
        switch (method.getName()) {
            case "tst_repositorySearch_Via":
                return new Object[][]{
                        { "repositoriesPage" },
                        { "searchingSidebar" },
                        {"headerNavSearchInput"}//github bug??? github does not find repository using this type of search

                };
            case "tst_repositoryDeletion":
                return new Object[][]{
                        { "searchingSidebar" }
                };
        }
        return new Object[][]{};
    }
}
