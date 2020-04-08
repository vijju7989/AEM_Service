# SealedAir AEM Codebase

This is a project template for AEM-based applications. It is intended as a best-practice set of examples as well as a potential starting point to develop your own functionality.

## Modules

The main parts of the template are:

* core: Java bundle containing all core functionality like OSGi services, listeners or schedulers, as well as component-related Java code such as servlets or request filters.
* ui.apps: contains the /apps (and /etc) parts of the project, ie JS&CSS clientlibs, components, templates, runmode specific configs as well as Hobbes-tests
* ui.content: contains sample content using the components from the ui.apps
* ui.tests: Java bundle containing JUnit tests that are executed server-side. This bundle is not to be deployed onto production.
* ui.launcher: contains glue code that deploys the ui.tests bundle (and dependent bundles) to the server and triggers the remote JUnit execution
* ui.frontend: an optional dedicated front-end build mechanism (Angular, React or general Webpack project)

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

If you have a running AEM instance you can build and package the whole project and deploy into AEM with

    mvn clean install -PautoInstallPackage

Or to deploy it to a publish instance, run

    mvn clean install -PautoInstallPackagePublish

Or alternatively

    mvn clean install -PautoInstallPackage -Daem.port=4503

Or to deploy only the bundle to the author, run

    mvn clean install -PautoInstallBundle

## Testing

There are three levels of testing contained in the project:

* unit test in core: this show-cases classic unit testing of the code contained in the bundle. To test, execute:

    mvn clean test

* server-side integration tests: this allows to run unit-like tests in the AEM-environment, ie on the AEM server. To test, execute:

    mvn clean verify -PintegrationTests

* client-side Hobbes.js tests: JavaScript-based browser-side tests that verify browser-side behavior. To test:

    in the browser, open the page in 'Developer mode', open the left panel and switch to the 'Tests' tab and find the generated 'MyName Tests' and run them.

## ClientLibs

The frontend module is made available using an [AEM ClientLib](https://helpx.adobe.com/experience-manager/6-5/sites/developing/using/clientlibs.html). When executing the NPM build script, the app is built and the [`aem-clientlib-generator`](https://github.com/wcm-io-frontend/aem-clientlib-generator) package takes the resulting build output and transforms it into such a ClientLib.

A ClientLib will consist of the following files and directories:

- `css/`: CSS files which can be requested in the HTML
- `css.txt` (tells AEM the order and names of files in `css/` so they can be merged)
- `js/`: JavaScript files which can be requested in the HTML
- `js.txt` (tells AEM the order and names of files in `js/` so they can be merged
- `resources/`: Source maps, non-entrypoint code chunks (resulting from code splitting), static assets (e.g. icons), etc.

## Maven settings

The project comes with the auto-public repository configured. To setup the repository in your Maven settings, refer to:

    http://helpx.adobe.com/experience-manager/kb/SetUpTheAdobeMavenRepository.html

## Git Workflow

Git is the tool we use to manage our code and the changes that developers make.

Documented below is our process on how to change branches, pull latest changes, create new branches, create commits etc.

### Switching Branches

When you first navigate to a folder which is version controlled by git via your terminal, you'll start on the `master` branch if you just cloned it, or you'll be on the branch you were last on when you were previously working.

For this project, the main branch in which all development occurs is on the `develop` branch, since `master` is typically reserved for production ready code and is only updated during each release.

To switch to the develop branch, run:

`git checkout develop`

If you forget what branch you are on, or what changes you have made, you can run:

`git status`

and it will tell you `On branch <branch-name>` on the first line.

### Obtaining Latest Changes

Before creating a new branch, it's best to make sure your `develop` branch is up to date with the latest code changes.

To obtain the latest changes, make sure you are on the `develop` branch and run:

`git pull`

Git will now either show you all the files that were updated, or it will say `Already up to date.` if you are already on the latest changes.

### Creating a new branch

When starting a new ticket, it's best to create a new branch specifically for that ticket.

Before creating your branch, make sure you start out on the `develop` branch and that it is up-to-date. See the above two sections for how to do that if you aren't sure how.

Assuming you are on the `develop` branch and are up-to-date, you can create a new branch by running the command:

`git checkout -b <branch-name>`

This will create a new branch with the name you provided and will automatically switch to the branch you just created.

When starting a new branch for a story it is best practice to create it off of the develop branch so that your new branch will start off with the most up-to-date and approved code.

**Note:** Remember that you can always check to see what branch you are on by running `git status` if you ever forget!

#### Branch Naming Conventions

For this project, and many other projects, the name of your branch should always start with the ticket number you are working on.

For example, let's say the ticket you are working on is `DCM-290`, then you would create your branch like this:

`git checkout -b DCM-290-layout-container-prototype`

The general standard is to have your branch name is `JIRA-#-short-description`

This way you can remember what ticket number corresponded to what kind of changes you were making.

### Committing your changes

Committing your code in git is a way of saving your code changes at a particular point.

While working on a ticket, you can commit once you are finished, or you can make smaller commits as you work through the whole ticket.

Either way is works, but committing more often is generally a better idea as if you need to go back to an earlier state of your code - say you find out the last half of your solution doesn't work, but the first half of your changes does - then you can roll back to an earlier commit you made rather than having to hunt down and delete all the changes you don't need anymore.

#### Tracking Stages

Edited Code is tracked in 3 stages:

1. Unstaged - You made edits, but you haven't told git to save them yet
2. Staged - You have told git that you are *ready* to save these changes
3. Committed - You have told git to save these changes and are associated with a commit

#### Staging Changed Files
Let's say you have made some changes and are now ready to commit.

Run a `git status` and it will tell you all the files you have changed and if you added any new files.

To have a file that you changed / added ready to be committed, i.e staged, then run:

`git add <path/to/changedFile>`

If you do a `git status` again, you will see that the file you added is now staged, i.e ready to be committed.

If you do a `git status` and want to add all the files that you have changed, then you can use this shortcut command:

`git add .`

This will prevent you from having to add each file individually, but be careful! It will add all the files you made changes to, so make sure you are aware of what you changed!

#### Committing Your Staged Files

Once you have the files you want to commit staged, you can then commit them.

Commits will save the changes you made to your files and will have a message associated with them to help developers know what the changes were related to.

Let's say you just finished creating a dialog for your component and have staged the files you are ready to commit.

To commit your would run:

`git commit -m "<your message here inside quotes>"`

For example, if you created the dialog for your component, your message might look like:

`git commit -m "Created the component dialog for layout container"`

When working on tickets, its best practice to include the ticket name like so:

`git commit -m "DCM-290: Created the component dialog for layout container"`

This helps developers on your team for if you are viewing changes in a particular file and want to know which changes were associated with what ticket, they are in the commits themselves.

And that's it! Your changes are now committed and saved.

### Pushing Your Code

After you have committed your changes, you will need to push your branch to the server where your Git repository is hosted.

To push your code, just do:

`git push`

If your branch isn't on the remote sever where your Git repository is hosted yet, then you will need to create the branch on the server so that you can push your changes there.

To create your branch on the server and push your changes at the same time, run:

`git push --set-upstream origin <branch-name>`

For example, lets say your branch is `DCM-290`, then you would run:

`git push --set-upstream origin DCM-290`

That will create the branch on the remote server, and will also push your changes.

If you make additional commits after creating the branch on the server, then just `git push` will work.

### Steps to rebase a branch

Let's say that you have created branch, DCM-101, off of develop and changes were merged into develop after. Before you can merge your DCM-101 branch, you need to get the latest code from develop. We do this with a rebase.

First, you need to commit or stash your local changes on DCM-101.

To stash, run:

`git stash`

Next you will run the following steps to rebase your branch off of develop:

1. `git fetch -p`
2. `git checkout develop`
3. `git rebase origin/develop`
4. `git checkout <your branch>`
5. `git rebase origin/develop`

    \* If you have stashed changes run `git stash pop` to put the changes back on your branch.

This will rewind your branch, apply the new changes from develop and then put your code on top of that.

To push these changes you will need to do a force push:

`git push -f`
