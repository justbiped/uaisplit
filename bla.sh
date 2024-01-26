echo Creating the gradle commit to bump app version to $1

git checkout master
git checkout automation
git checkout master
git add version.properties
git commit -m "Bump app version to $1"
git push origin automation
