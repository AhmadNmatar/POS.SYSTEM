# POS.SYSTEM

A Java-based Point-of-Sale (POS) system for sales and checkout scenarios. The application demonstrates a layered design with a controller, domain model, external-system integrations, console-style view, and startup entry point.

## Features

- Start and complete sales
- Add items by item identifier
- Calculate prices and quantities
- Apply discounts through an integration database
- Process cash payments
- Calculate change
- Generate receipts
- Print sale information
- Log total revenue and runtime errors
- Include JUnit tests for core application components

## Technology

- Java 19
- Maven
- JUnit Jupiter 5.6.0
- Maven Surefire Plugin 2.22.0

## Project Structure

```text
src/
├── main/java/se/kth/iv1350/amazingpos/
│   ├── controller/     # Coordinates operations between view and model
│   ├── integration/    # Inventory, accounting, discounts, printer, and logging
│   ├── model/          # Sale, receipt, payment, quantity, and price logic
│   ├── startup/        # Application entry point
│   ├── util/           # Logging support
│   └── view/           # Demo/view behavior and output handlers
└── test/java/se/kth/iv1350/amazingpos/
    ├── controller/
    ├── integration/
    ├── model/
    └── startup/
```

## Prerequisites

- JDK 19 or later
- Maven 3.6 or later

Check the installed tools:

```bash
java -version
mvn -version
```

## Getting Started

Clone the repository:

```bash
git clone https://github.com/AhmadNmatar/POS.SYSTEM.git
cd POS.SYSTEM
```

Build the project:

```bash
mvn clean package
```

Run the application through Maven:

```bash
mvn exec:java
```

The Maven configuration identifies `se.kth.iv1350.amazingpos.startup.Main` as the main class.

## Running Tests

Run all JUnit tests:

```bash
mvn test
```

## Generated and Runtime Files

The application includes example runtime output and logging files at the repository root:

- `POS System-Log.txt` — application and exception log
- `TotalRevenue.txt` — accumulated revenue output
- `target/` — Maven build artifacts

## Development Notes

- The project is configured as a Maven JAR application.
- Source and test code use the package prefix `se.kth.iv1350.amazingpos`.
- The application follows a separation between controller, view, model, and integration layers.
- Some integrations are implemented as local demonstration components rather than connections to real external services.

## Contributing

1. Fork the repository.
2. Create a feature branch.
3. Add or update tests where applicable.
4. Run `mvn test` before submitting changes.
5. Open a pull request with a clear description.

## Author

[AhmadNmatar](https://github.com/AhmadNmatar)

## Repository

[https://github.com/AhmadNmatar/POS.SYSTEM](https://github.com/AhmadNmatar/POS.SYSTEM)
