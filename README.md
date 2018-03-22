# Event Bus PoC

Event bus proof of concept with RabbitMQ and Spring Boot microservices. 

* **`fcproxy_service`**

    * Rest controller **`POST /user/logged-in`**.
        * Publishes an **`UserLoggedIn`** event to the event bus.

* **`user_service`**

    * Listens to the event bus for **`UserLoggerIn`** events.
        * Writes the user to **`user_database`**.
        * Publishes an event **`UserCreated`** or **`UserUpdated`**.

* **`basket_service`**

    * Listens to the event bus for **`UserCreated`** or **`UserUpdated`** events.
        * Writes the user to **`basket_database`**.

* **`user_database`**

    * MySQL database. Only table **`users`**.

* **`basket_database`**

    * MySQL database. Table **`users`** and more.

* **`event_bus`**

    * RabbitMQ server.