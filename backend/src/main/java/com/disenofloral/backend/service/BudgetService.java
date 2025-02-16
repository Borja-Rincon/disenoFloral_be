package com.disenofloral.backend.service;

import com.disenofloral.backend.model.Budget;
import com.disenofloral.backend.model.Event;
import com.disenofloral.backend.model.User;
import com.disenofloral.backend.repository.BudgetRepository;
import com.disenofloral.backend.repository.EventRepository;
import com.disenofloral.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Optional<Budget> getBudgetById(Long id) {
        return budgetRepository.findById(id);
    }

    public Budget createBudget(Budget budget) {
        if (budget.getUserId() != null) {
            User user = userRepository.findById(budget.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + budget.getUserId()));
            budget.setUser(user);
        }
        if (budget.getEventId() != null) {
            Event event = eventRepository.findById(budget.getEventId())
                    .orElseThrow(() -> new RuntimeException("Event not found with id: " + budget.getEventId()));
            budget.setEvent(event);
        }
        return budgetRepository.save(budget);
    }

    public Budget updateBudget(Long id, Budget budgetDetails) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found with id: " + id));

        budget.setTitle(budgetDetails.getTitle());
        budget.setAmount(budgetDetails.getAmount());

        if (budgetDetails.getUserId() != null) {
            User user = userRepository.findById(budgetDetails.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + budgetDetails.getUserId()));
            budget.setUser(user);
        }

        if (budgetDetails.getEventId() != null) {
            Event event = eventRepository.findById(budgetDetails.getEventId())
                    .orElseThrow(() -> new RuntimeException("Event not found with id: " + budgetDetails.getEventId()));
            budget.setEvent(event);
        }

        return budgetRepository.save(budget);
    }

    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
}
