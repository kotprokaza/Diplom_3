#!/bin/bash

echo "🚀 ЗАПУСК ТЕСТОВ ДЛЯ ЗАДАНИЯ 3"
echo "=============================="
echo ""

echo "1. Тестирование в Chrome..."
echo "--------------------------"
mvn clean test -Dbrowser=chrome

echo ""
echo "2. Генерация Allure отчета..."
echo "---------------------------"
mvn allure:report

echo ""
echo "3. Открытие отчета..."
echo "--------------------"
if command -v open &> /dev/null; then
open target/site/allure-maven/index.html
elif command -v xdg-open &> /dev/null; then
xdg-open target/site/allure-maven/index.html
else
echo "Отчет создан: file://$(pwd)/target/site/allure-maven/index.html"
fi

echo ""
echo "🎉 ТЕСТИРОВАНИЕ ЗАВЕРШЕНО!"
echo "Для тестирования в Яндекс.Браузере выполните:"
echo "mvn clean test -Dbrowser=yandex"
