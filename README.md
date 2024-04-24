# AIImpementation

This project showcases a list of posts downloaded from below URI in a batch of 10. It also shows Pagination support where the data is more and loading the data in batches.

## Project Platform

This project is in Android (Kotlin)

## Backend Used

It uses Jsonplaceholder APIs to fetch the posts.

Base URL: https://jsonplaceholder.typicode.com/

End URL: posts?_page=<PAGE_NUM>

## Implementation

It uses retrofit to fetch the data from Jsonplaceholder API server.

It makes use of Recycler View to show the list of Posts fetched.

It uses a nested scroll view to track the end of scrolling of the Recycler view in order to fetch next set of posts.

Currently due to time constraint, I have not used View Model for handling the data, all the code is in Main Activity.

## Screenshots

Screenshots can also be found in Screenshots/ folder in the repo. Below are the screenshots

![alt text](/Screenshots/Img1.jpeg)

![alt text](/Screenshots/Img2.jpeg)

![alt text](/Screenshots/Img3.jpeg)

![alt text](/Screenshots/Img4.jpeg)

![alt text](/Screenshots/Img5.jpeg)

![alt text](/Screenshots/Img6.jpeg)

![alt text](/Screenshots/Img7.jpeg)

![alt text](/Screenshots/Img8.jpeg)

