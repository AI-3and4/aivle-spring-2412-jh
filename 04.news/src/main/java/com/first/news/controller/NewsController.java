package com.first.news.controller;

import com.first.news.domain.News;
import com.first.news.dto.NewsDto;
import com.first.news.mapper.NewsMapper;
import com.first.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsRepository newsRepository;
    private final NewsMapper mapper;

    @GetMapping("/new")
    public String newArticleForm() {
        return "news/new";
    }
    @PostMapping("/create")
    public String createNews(NewsDto.Post post){
        System.out.println(post);
        News news = mapper.newsPostDtoToNews(post);
        newsRepository.save(news);
        return "redirect:/news/" + news.getNewsId();
    }

    @GetMapping("/{newsId}")
    public String getNews(@PathVariable("newsId")Long newsId, Model model) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        model.addAttribute("news", news);
        return "news/detail";
    }

    @GetMapping("/list")
    public String newsList(Model model, @RequestParam(name="page", defaultValue = "0") int page) {


        Pageable pageable = PageRequest.of(page - 1, 7);
        Page<News> newsPage = newsRepository.findAll(pageable);

        int totalPages = newsPage.getTotalPages(); // 전체 페이지 수

        // 페이지 번호들과 현재 페이지 여부를 저장할 리스트
        //List<Map<String, Object>> pageNumbers = IntStream.rangeClosed(1, totalPages)
        // .mapToObj(pageNum -> {
        // Map<String, Object> pageMap = new HashMap<>();
        // pageMap.put("pageNumber", pageNum);
        // pageMap.put("isCurrentPage", pageNum == page);
        //
        // return pageMap;

        model.addAttribute("newsPage", newsPage);

        model.addAttribute("prev", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", newsPage.hasNext());
        model.addAttribute("hasPrev", newsPage.hasPrevious());
        return "news/list";
    }
}
