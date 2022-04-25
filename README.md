# 50001-InfoSys1D - FEAST App
This repo contains two source folders - FEAST and openCV
- FEAST folder contains codebase and resources for the FEAST Android App.
- openCV folder contains openCV crowd tracker implementation.

## Contributors
This project was made for 50.001 Information Systems & Programming 1D Project by Team1D Cohort 1
 - [Michael Chun](https://github.com/mckp0) 1005258 [Project Lead]
 - [Christopher Wah](https://github.com/skerbos) 1005033 [Backend & Firebase Lead]
 - [Pala Tej Deep](https://github.com/Tej-Deep) 1005282 [Backend and openCV Developer]
 - [Javier Teo](https://github.com/javiertzr01) 1005214 [Backend Developer]
 - [Lim Fuo En](https://github.com/Fe-56) 1005125 [Frontend Lead] 
 - [Tan Lay Lin](https://github.com/Lay-Lin) 1005474 [Frontend Developer]
 - [Kenny Ong Ker Chin](https://github.com/cannotknee) 1005290 [Frontend Developer]

## Problem Statement
With large crowds and limited capacity being inconvenient for diners, how can we design an app to seamlessly allow diners to make informed decisions on where and when to eat?

## Solution
Introducing FEAST, Food Establishment Autonomous Spatial Tracking, a real-time crowd tracking app. Features F&B establishments in SUTD with up to date information on opening/closing times as well as daily specials, current crowd levels as well as historic crowd data levels.

## Project Description
Our application, Food Establishment Automated Spatial Tracking (FEAST),  thus aims to address these problems by allowing diners to seamlessly make informed decisions on where and when to eat. By providing real time information at a particular food establishment, such as crowd capacities and estimated waiting times, users can decide or plan ahead to avoid wasting time queueing or finding a spot to eat.

## How to run
1. FEAST folder can be opened using Android Studio
2. The app can be run on an emulator or Android device with minimum API level 30
3. The app draws data from our Firebase [database](https://feastbackend-default-rtdb.firebaseio.com/)
    - note that this Firebase is made public for any user to edit
4. Run `run.py` in the directory `50001-InfoSys1D/openCV/People-Counting-in-Real-Time-master/` to initiate openCV crowd tracker.
5. The database will be automatically fed crowd level data based on the openCV crowd tracker.

## System Design
Our system design is broken into 3 sections, the hardware, database and the client application itself. The hardware consists of a Raspberry Pi 4 with a camera module, which runs an OpenCV python program that allows it to count people entering and leaving a premise in real time. The real time information is then sent to the database, which uses Firebase’s real time database. Finally, the client application reads the database for the real time information, processes it and provides concise information for the user.

![System Design Architecture](https://user-images.githubusercontent.com/68381057/164953704-baed6f7d-f55e-489f-93cf-e67b2a31ae61.png)

## Resources
openCV Python implementation 
- https://github.com/saimj7/People-Counting-in-Real-Time
- https://www.pyimagesearch.com/2018/08/13/opencv-people-counter/

Firebase Database
- https://firebase.google.com/docs/database

This project was made for 50.001 Information Systems & Programming 1D Project by Team1D
