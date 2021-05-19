# AndriodMyLibrary
App project for AND1

Description:
HomeLibrary is an app for people who want help organizing their many books or readers who want help keeping track of the many books they read or both. 
I am a part of both groups and have often struggled to keep a good overview of what I own versus what I have read.
In this app, you can keep track of which books who you own or want to, what you are currently reading, have read or have not read yet. 
You can add books to your library with the following information: title, bookcover, author, price, read status, owned status, rating, pagecount. You can upload images from your phone's gallery.
Your library has a filter function so you can search based on the information you have given about the book. 
Furthermore, in HomeLibrary, you can seek inspiration to expand library or your reading list. You just enter a keyword for what you are seeking: e.g. android, tolkien or non-fiction
and the app will list some books that matches your search criteria.

# Demonstration:
https://www.youtube.com/watch?v=44q2MWVbdEY

# Requirements:

High(Must have):
1. As a user, I want to able to sign up, sign in and sign out because this allows my library to be private.                                               | Implemented
2. As a user, I want to be able to add, see, edit and delete a book to my library with the following information: 
title, bookcover, author, price, read status, owned status, rating, pagecount because I want to store relevant information about my books.                | Implemented
3. As a user, I want to be able to filter through my library based on the following information(title, author, read status, owned status, rating)         | Implemented
because I want to be able to find specific books.

Medium(Should have): 
1. (4.) As a user, I want to be able track which books I am currently reading and what page I am because that is more convenient than remembering it myself.   | Implemented
2. (5.) As a user, I want to be able to upload a picture from my phone as bookcover because I want identify my books by their cover.                           | Implemented
3. (6.) As a user, I want to be able to search after new books and read the following information(title, bookcover, author, pagecount, genre, summary, price) 
because I want inspiration to expand my library through Google Books API.                                                                                 | Implemented

Low(Could have): 
1. (7.) As a user, I want to be able to add a book from Google Books API to my library so I want can spare some time.                                          | Implemented 
2. (8.) As a user, I want to be able to upload a profile picture and change my username because I want to be control how my profile comes across to the users. | Not Implemented

Very low(Wont have)
1. (9.) As a user, I want to be to befriend, unfriend and see other users' profiles because I want to be able to meet likeminded people.                       | Not Implemented
2. (10.) As a user, I want to be able see my friends libraries because I can see what they have and how they have rated.                                       | Not Implemented


# References:
Firebase Authentication is heavily inspired by https://github.com/KasperKnop/GoogleServicesExample
GUI to Google Books API inspired by https://www.youtube.com/watch?v=vUMvWYzlgOs&feature=youtu.be
Storing and retrieving images from fire storage inspired by https://www.youtube.com/playlist?list=PLrnPJCHvNZuB_7nB5QD-4bNg6tpdEUImQ
