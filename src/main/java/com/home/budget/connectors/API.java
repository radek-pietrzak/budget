package com.home.budget.connectors;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
public interface API {

    String TRANSACTIONS = "/home-budget/transactions";
    String TRANSACTIONS_ID = "/home-budget/transactions/{id}";
    String MULTI_TRANSACTIONS = "/home-budget/multi-transactions";
    String EXPENSES = "/home-budget/expenses";
    String EXPENSES_ID = "/home-budget/expenses/{id}";

    String INCOMES = "/home-budget/incomes";
    String INCOMES_ID = "/home-budget/incomes/{id}";

    String INVESTMENTS = "/home-budget/investments";
    String INVESTMENTS_ID = "/home-budget/investments/{id}";

    String SAVINGS = "/home-budget/savings";
    String SAVINGS_ID = "/home-budget/savings/{id}";

    String EXPENSE_CATEGORIES = "/home-budget/expense-categories";
    String EXPENSE_CATEGORIES_ID = "/home-budget/expense-categories/{id}";

    String INCOME_CATEGORIES = "/home-budget/income-categories";
    String INCOME_CATEGORIES_ID = "/home-budget/income-categories/{id}";

    String INVESTMENT_CATEGORIES = "/home-budget/investment-categories";
    String INVESTMENT_CATEGORIES_ID = "/home-budget/investment-categories/{id}";

    String SAVING_CATEGORIES = "/home-budget/saving-categories";
    String SAVING_CATEGORIES_ID = "/home-budget/saving-categories/{id}";

    String PAY_METHODS = "/home-budget/pay-methods";
    String PAY_METHODS_ID = "/home-budget/pay-methods/{id}";

    String LOGOUT = "/home-budget/logout";


}
