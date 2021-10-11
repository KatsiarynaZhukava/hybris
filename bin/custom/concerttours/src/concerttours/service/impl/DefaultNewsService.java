package concerttours.service.impl;
import java.util.Date;
import java.util.List;
import concerttours.daos.NewsDAO;
import concerttours.model.NewsModel;
import concerttours.service.NewsService;

import javax.annotation.Resource;

public class DefaultNewsService implements NewsService
{
    @Resource
    private NewsDAO newsDAO;

    @Override
    public List<NewsModel> getNewsOfTheDay(final Date date)
    {
        return newsDAO.getNewsOfTheDay(date);
    }

    public void setNewsDAO(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }
}