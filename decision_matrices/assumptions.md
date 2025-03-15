## Assumptions
To conduct proper decisions some assumptions had to be made, due to lack of Business Logic knowledge.
The assumptions are listed below.
- API level assumptions
    - JSON schemas provided are correct
    - For `int32` properties, no min/max value was specifically declared inside the JSON schemas, so both `Integer.MAX_VALUE` and `Integet.MIN_VALUE` were considered as valid values.
    - For `POST` and `PUT` requests, the fact that inside the request body, the `id` of the resource was provided, it was considered valid
    - The endpoints process 1 error at a time
- Business level assumptions
    - The backend always returns the same data, so no CRUD flow tests were implemented
    - No tests were conducted based on Business logic, for example:
        - The `id` property sent **inside the request body** of `POST` request, pointing to already existing id
        - The `publishDate` is in the future
        - ids having negative/zero values were considered as valid
        - e.t.c
