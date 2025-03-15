Below are the decision matrices for the endpoints of the Books API.

> Symbols used:
> - `T`(true): condition satisfied
> - `F`(false): condition not satisfied
> - `N/A`: condition is infeasible for the given rule
> - `-` : value of condition it’s irrelevant for the action outcome
> - `X`: action should occur
> - ` ` (empty): action should not occur

## Decision matrix for GET /api/v1/Books

| Conditions     | Rule 1 |
|----------------|--------|
| Get request    | T      |
|                |        |
| **Actions**    |        |
| Valid response | X      |

## Decision matrix for GET and DELETE /api/v1/Books/{id}

| Conditions                                                                                                  | Rule 1 | Rule 2 | Rule 3 |
|-------------------------------------------------------------------------------------------------------------|--------|--------|--------|
| id pointing to existing book                                                                                | T      | N/A    | N/A    |
| id pointing to non existing book<br/>-id=`Integer.MAX_VALUE`<br/>-id=0<br/>id=`Integer.MIN_VALUE`           | N/A    | T      | N/A    |
| id is invalid<br/>-id=`Integer.MAX_VALUE+1`<br/>-id=`Integer.MIN_VALUE-1`<br/>-is alphanumeric (e.g. "12a") | N/A    | N/A    | T      |
| **Actions**                                                                                                 |        |        |        |
| Valid response                                                                                              | X      |        |        |
| Not found response                                                                                          |        | X      |        |
| Id not valid response                                                                                       |        |        | X      |     

## Decision matrix for POST /api/v1/Books
| Conditions                                                                                                                                                                                                                                               | Rule 1 | Rule 2 | Rule 3 | Rule 4 | Rule 5 | Rule 6 | Rule 7 |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------|--------|--------|--------|--------|--------|--------|
| `id` valid:<br/>-id=`Integer.MAX_VALUE`<br/>-id=0<br/>id=`Integer.MIN_VALUE`                                                                                                                                                                             | T      | N/A    | -      | -      | -      | -      | -      |
| `id` not valid<br/>-id=`Integer.MAX_VALUE+1`<br/>-id=`Integer.MIN_VALUE-1`<br/>-alphanumeric (e.g. `"something"`)<br/>-integer but passed as string (e.g. `"12"`)<br/>-is boolean<br/>-is float<br/>-is a json object<br/>-is json array                 | N/A    | T      | -      | -      | -      | -      | -      |
| `pageCount` valid:<br/>-pageCount=`Integer.MAX_VALUE`<br/>-pageCount=0<br/>pageCount=`Integer.MIN_VALUE`                                                                                                                                                 | T      | -      | N/A    | -      | -      | -      | -      |
| `pageCount` not valid<br/>-pageCount=`Integer.MAX_VALUE+1`<br/>-pageCount=`Integer.MIN_VALUE-1`<br/>-alphanumeric (e.g. `"something"`)<br/>-integer but passed as string (e.g. `"12"`)-is boolean<br/>-is float<br/>-is a json object<br/>-is json array | N/A    | -      | T      | -      | -      | -      | -      |
| `title` valid:<br/>-null<br/>-empty<br/>-not null & not empty                                                                                                                                                                                            | T      | -      | -      | N/A    | -      | -      | -      |
| `title` not valid:<br/>-is number<br/>-is boolean<br/>-is json array<br/>-is json object                                                                                                                                                                 | N/A    | -      | -      | T      | -      | -      | -      |
| `description` valid:<br/>-null<br/>-empty<br/>-not null & not empty                                                                                                                                                                                      | T      | -      | -      | -      | N/A    | -      | -      |
| `description` not valid:<br/>-is number<br/>-is boolean<br/>-is json array<br/>-is json object                                                                                                                                                           | N/A    | -      | -      | -      | T      | -      | -      |
| `excerpt` valid:<br/>-null<br/>-empty<br/>-not null & not empty                                                                                                                                                                                          | T      | -      | -      | -      | -      | N/A    | -      |
| `excerpt` not valid:<br/>-is number<br/>-is boolean<br/>-is json array<br/>-is json object                                                                                                                                                               | N/A    | -      | -      | -      | -      | T      | -      |
| `publishDate` valid:<br/>-valid format                                                                                                                                                                                                                   | T      | -      | -      | -      | -      | -      | N/A    |
| `publishDate` not valid:<br/>-null<br/>-empty<br/>-not valid format<br/>-is number<br/>-is boolean<br/>-is json array<br/>-is json object                                                                                                                | N/A    | -      | -      | -      | -      | -      | T      |
| **Actions**                                                                                                                                                                                                                                              |        |        |        |        |        |        |        |
| Valid response                                                                                                                                                                                                                                           | X      |        |        |        |        |        |        |
| `id` not valid response                                                                                                                                                                                                                                  |        | X      |        |        |        |        |        |
| `pageCount` not valid response                                                                                                                                                                                                                           |        |        | X      |        |        |        |        |
| `title` not valid response                                                                                                                                                                                                                               |        |        |        | X      |        |        |        |
| `description` not valid response                                                                                                                                                                                                                         |        |        |        |        | X      |        |        |
| `excerpt` not valid response                                                                                                                                                                                                                             |        |        |        |        |        | X      |        |
| `publishDate` not valid response                                                                                                                                                                                                                         |        |        |        |        |        |        | X      | 

From the above decision matrix, we see that we can have:
- 243 positive cases (the endpoint returns a 200 response)
- 34 negative cases (the endpoint returns an error response)

We can drastically reduce the amount of positive cases to 3 test cases, by choosing to test each
valid value only once. For example, we cn have the following 3 cases:

#### Case 1
- `id = Integer.MAX_VALUE`
- `pageCount = Integer.MAX_VALUE`
- `title = empty`
- `description = empty`
- `excerpt = empty`
- `publishDate` valid

#### Case 2
- `id = 0`
- `pageCount = 0`
- `title = null`
- `description = null`
- `excerpt = null`
- `publishDate` valid

#### Case 3
- `id = Integer.MIN_VALUE`
- `pageCount = Integer.MIN_VALUE`
- `title = not null & not empty`
- `description = not null & not empty`
- `excerpt = not null & not empty`
- `publishDate` valid

## Decision matrix for PUT /api/v1/Books/{id}
It is a combination of the decision matrices of `GET /api/v1/Books/{id}` and `PUT /api/v1/Books/{id}`