package com.sujay.journalApplication.Scheduler;

import com.sujay.journalApplication.Entity.JournalEntry;
import com.sujay.journalApplication.Entity.User;
import com.sujay.journalApplication.Repository.UserRepositoryImpl;
import com.sujay.journalApplication.Service.EmailService;
import com.sujay.journalApplication.Service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    //cron expression to schedule a mail every Sunday at 9:00 AM
    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSAEMAil() {
        List<User> users = userRepository.getUserForSentimentAnalysis();
        for (User user: users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredUserEntries = journalEntries.stream().filter(x -> x.getDate()
                    .isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(x -> x.getContent()).collect(Collectors.toList());

            String entry = String.join(" ", filteredUserEntries);
            String sentiment = sentimentAnalysisService.getSentiment(entry);

            emailService.SendEmail(user.getEmail(), "Sentiment for last 7 days: ", sentiment);
        }
    }
}