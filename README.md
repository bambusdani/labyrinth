Command line instructions


Git global setup

git config --global user.name "Name"
git config --global user.email "E-Mail Adresse"

Create a new repository

git clone git@gitlab.com:dvl2/labyrinth.git
cd labyrinth
touch README.md
git add README.md
git commit -m "add README"
git push -u origin master

Existing folder or Git repository

cd existing_folder
git init
git remote add origin git@gitlab.com:dvl2/labyrinth.git
git add .
git commit
git push -u origin master