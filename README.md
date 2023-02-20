
# mobile-feedback

## Endpoints

### POST /mobile-feedback/feedback/:origin

In order to trigger a 204 No Content a feedback model must also be provided
```json
{
  "ableToDo": true,
  "howEasyScore": 5,
  "whyGiveScore": "It was great",
  "howDoYouFeelScore": 4
}
```
#### Response
- 204 - No Content

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").