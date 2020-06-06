# Quiz
An application that allows you to manage quizzes - 
add, delete, display and solve them.

### Endpoints
All not `GET` endpoints accepts parameters as json (`Content-type application/json`).

The application is secured with HTTP Basic authorization. 
All endpoints expect `/api/register` have required authorization.

- Quiz Controller:
    - `GET /api/quizzes` - getting all quizzes,
    - `GET /api/quizzes/{id}` - getting quiz with id,
    - `GET /api/quizzes/completed` - getting completed quizzes,
    - `POST /api/quizzes` - quiz adding,
    - `POST /api/quizzes/{id}/solve` - solving quiz with id,
    - `DELET /api/quizzes/{id}` - deleting quiz with id (only if user is quiz author),
- User Controller:
    - `POST /api/register` - registering user,
    