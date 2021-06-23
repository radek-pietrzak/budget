package com.home.budget.controllers;

public interface API {
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


}