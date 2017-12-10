const addArticleListHandlers = () => {
    const navButtons = document.querySelectorAll(".article-list .nav .tab");
    navButtons.forEach((button, index) => button.addEventListener("click", () => {
        // Hide all article lists
        document.querySelectorAll(".article-list .list").forEach(list => list.classList.add("hidden"));
        // Show the list related to this button
        document.querySelectorAll(".article-list .list")[index].classList.remove("hidden");
        // Remove selected from all nav buttons
        navButtons.forEach(tab => tab.classList.remove("selected"));
        // Add selected to the clicked nav button
        button.classList.add("selected");
    }));
};
addArticleListHandlers();
