Below are the decision matrices for the endpoints of the Authors API.

> Symbols used:
> - `T`(true): condition satisfied
> - `F`(false): condition not satisfied
> - `N/A`: condition is infeasible for the given rule
> - `-` : value of condition it’s irrelevant for the action outcome
> - `X`: action should occur
> - ` ` (empty): action should not occur

## Decision matrix for GET /api/v1/Authors

| Conditions     | Rule 1 |
|----------------|--------|
| Get request    | T      |
|                |        |
| **Actions**    |        |
| Valid response | X      |

## Decision matrix for GET and DELETE /api/v1/Authors/{id}

| Conditions                                                                                                  | Rule 1 | Rule 2 | Rule 3 |
|-------------------------------------------------------------------------------------------------------------|--------|--------|--------|
| id pointing to existing author                                                                              | T      | N/A    | N/A    |
| id pointing to non existing author<br/>-id=`Integer.MAX_VALUE`<br/>-id=0<br/>id=`Integer.MIN_VALUE`         | N/A    | T      | N/A    |
| id is invalid<br/>-id=`Integer.MAX_VALUE+1`<br/>-id=`Integer.MIN_VALUE-1`<br/>-is alphanumeric (e.g. "12a") | N/A    | N/A    | T      |
| **Actions**                                                                                                 |        |        |        |
| Valid response                                                                                              | X      |        |        |
| Not found response                                                                                          |        | X      |        |
| Id not valid response                                                                                       |        |        | X      |     

## Decision matrix for POST /api/v1/Authors
| Conditions                                                                                                                                                                                                                                      | Rule 1 | Rule 2 | Rule 3 | Rule 4 | Rule 5 |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------|--------|--------|--------|--------|
| `id` valid:<br/>-id=`Integer.MAX_VALUE`<br/>-id=0<br/>id=`Integer.MIN_VALUE`                                                                                                                                                                    | T      | N/A    | -      | -      | -      |
| `id` not valid<br/>-id=`Integer.MAX_VALUE+1`<br/>-id=`Integer.MIN_VALUE-1`<br/>-alphanumeric (e.g. `"something"`)<br/>-integer but passed as string (e.g. `"12"`)<br/>-is boolean<br/>-is float<br/>-is a json object<br/>-is json array        | N/A    | T      | -      | -      | -      |
| `idBook` valid:<br/>-idBook=`Integer.MAX_VALUE`<br/>-idBook=0<br/>idBook=`Integer.MIN_VALUE`                                                                                                                                                    | T      | -      | N/A    | -      | -      |
| `idBook` not valid<br/>-idBook=`Integer.MAX_VALUE+1`<br/>-idBook=`Integer.MIN_VALUE-1`<br/>-alphanumeric (e.g. `"something"`)<br/>-integer but passed as string (e.g. `"12"`)-is boolean<br/>-is float<br/>-is a json object<br/>-is json array | N/A    | -      | T      | -      | -      |
| `firstName` valid:<br/>-null<br/>-empty<br/>-not null & not empty                                                                                                                                                                               | T      | -      | -      | N/A    | -      |
| `firstName` not valid:<br/>-is number<br/>-is boolean<br/>-is json array<br/>-is json object                                                                                                                                                    | N/A    | -      | -      | T      | -      |
| `lastName` valid:<br/>-null<br/>-empty<br/>-not null & not empty                                                                                                                                                                                | T      | -      | -      | -      | N/A    |
| `lastName` not valid:<br/>-is number<br/>-is boolean<br/>-is json array<br/>-is json object                                                                                                                                                     | N/A    | -      | -      | -      | T      |
| **Actions**                                                                                                                                                                                                                                     |        |        |        |        |        |
| Valid response                                                                                                                                                                                                                                  | X      |        |        |        |        |
| `id` not valid response                                                                                                                                                                                                                         |        | X      |        |        |        |
| `idBook` not valid response                                                                                                                                                                                                                     |        |        | X      |        |        |
| `firstName` not valid response                                                                                                                                                                                                                  |        |        |        | X      |        |
| `lastName` not valid response                                                                                                                                                                                                                   |        |        |        |        | X      |

From the above decision matrix, we see that we can have:
- 81 positive cases (the endpoint returns a 200 response)
- 23 negative cases (the endpoint returns an error response)

We can drastically reduce the amount of positive cases to 3 test cases, by choosing to test each
valid value only once. For example, we cn have the following 3 cases:

#### Case 1
- `id = Integer.MAX_VALUE`
- `idBook = Integer.MAX_VALUE`
- `firstName = empty`
- `lastName = empty`

#### Case 2
- `id = 0`
- `idBook = 0`
- `firstName = null`
- `lastName = null`

#### Case 3
- `id = Integer.MIN_VALUE`
- `idBook = Integer.MIN_VALUE`
- `firstName = not null & not empty`
- `lastName = not null & not empty`

## Decision matrix for PUT /api/v1/Authors/{id}
It is a combination of the decision matrices of `GET /api/v1/Authors/{id}` and `PUT /api/v1/Authors/{id}`