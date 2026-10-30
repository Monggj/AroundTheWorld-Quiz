# Around The World – Country Information & Quiz App

A Java console application for learning about countries around the world. It reads country data from a CSV file and lets you browse, search, and test your knowledge with a food-based quiz.

## Features

- **List:** show all countries in the data file
- **Detail:** show the capital, traditional food, and traditional culture of a country
- **Search:** find countries by keyword (matches country name or food name)
- **Quiz:** a 4-choice quiz that asks "Which country is this food from?"
  - Wrong answers are chosen at random and never duplicate each other
  - The order of choices is shuffled every time
  - The food image opens automatically in your default image viewer
- Country data is stored in a CSV file, so you can add countries without changing the code

## Menu

| Key | Action |
|---|---|
| 1 | List all countries (一覧) |
| 2 | Show details of a country (詳細) – enter its ID number |
| 3 | Search by keyword (検索) |
| 4 | Start a quiz (クイズ) |
| 0 | Exit (終了) |

## Project Structure

```
AroundTheWorld/
├── src/
│   └── AroundTheWorld.java
├── img/                # food images used in the quiz
└── countries.csv       # country data
```

> The CSV file and the `img/` folder must be in the **project root folder** (next to `src/`). The program reads them by relative path.

## CSV Format

Each line is one country, separated by commas (no header row):

```
ID,Country,Capital,Food,Traditional,Image
```

| Column | Description |
|---|---|
| ID | Country number (integer) |
| Country | Country name |
| Capital | Capital city |
| Food | Traditional food |
| Traditional | Traditional culture or custom |
| Image | Image file name inside `img/` (optional) |

Notes:
- Do not use commas inside a field, because the file is split by commas.
- If the Image column is empty, the quiz still works but shows no picture.

## Requirements

- Java 8 or later
- Eclipse IDE (or any Java IDE)
- A desktop environment (needed to open images automatically)

## How to Run (Eclipse)

1. Clone or download this repository.
2. Import it into Eclipse as a Java project.
3. Check that the CSV file and the `img/` folder are in the project root.
4. Run `AroundTheWorld.java` as a Java Application.

## Tech Stack

- Java
- Eclipse IDE
- CSV (file-based data)

## Team

- Team project (2 member) – Thinh - Zin
