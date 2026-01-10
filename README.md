# AI Code Commenter

An intelligent application that uses AI to automatically generate meaningful comments for your code.

## Features

- Support for multiple programming languages (Java, Python, JavaScript, C#, TypeScript, Go, Rust, C++)
- AI-powered comment generation
- Console-based user interface
- Easy configuration

## Project Structure

```
AI-Code-Commenter/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/aicodecommenter/
│   │   │       ├── MainApp.java
│   │   │       ├── ui/
│   │   │       ├── service/
│   │   │       ├── api/
│   │   │       └── util/
│   │   └── resources/
│   │       └── config.properties
│   └── test/
│       └── java/
│           └── com/aicodecommenter/
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher

### Building

```bash
mvn clean build
```

### Running

```bash
mvn exec:java -Dexec.mainClass="com.aicodecommenter.MainApp"
```

### Running Tests

```bash
mvn test
```

## Configuration

Edit `src/main/resources/config.properties` to configure:
- API endpoint
- API key
- Timeout settings
- Logging level

## Dependencies

- Apache HttpComponents Client
- Google Gson
- JUnit 4

## License

MIT License

## Contributing

Contributions are welcome! Please feel free to submit a pull request.
