# Fashbot

This is a demo project completed as part of assignment for "Software Development & Quality Assurance" module of Universitat Autonoma de Barcelona.

## Introduction

The project as developed to serve as a backend for Dialog Flow chatbot, which is connect with with Facebook page - https://www.facebook.com/Entrada-de-Fashion-2092096347685911/.
The code style completly abides by SUN standards, has 95% test coverage and 1.5 cyclomatic complexity.

## HOW TO DEPLOY

You need to create a account on heroku. Update the git remote for heroku to your new app and push the repository.

```
git push heroku master
heroku logs --tail
```

The URL provided in logs is requied to be added as a webhook on dialog flow. All messages show start to flow through the `POST` method in `APIResource.java` class.
Currently code replies to 2 intents 
1. `Order Tracking` 
2. `Price Check`

