const topNav = document.getElementById("top-nav");

const responsiveNav = () => {
  if (topNav.classList.contains("responsive")){
    topNav.classList.remove("responsive");
  } else {
    topNav.classList.add("responsive");
  }
}

const navToSection = (elementSelector) => {
  jumpToSection(elementSelector);
  topNav.classList.remove("responsive");
}

const goToPageSection = (pagePath, sectionID) => {
  if (pagePath === window.location.path) {
    return navToSection(`#${sectionID}`);
  }
  window.location = pagePath + "#"+sectionID;
}

const windowResizeHandlerNavBar = () => {
  if (window.innerWidth > 700) {
    topNav.classList.remove("responsive");
  }
}

window.addEventListener("resize", windowResizeHandlerNavBar);

const navBarHeight = document.getElementById("top-nav").computedStyleMap().get("min-height")["value"];
const jumpToSection = (elementSelector) => {
  element = document.querySelector(elementSelector);
  const elemRect = element.getBoundingClientRect();
  scrollBy(0,elemRect.top-navBarHeight);
}

const docClickHandlerNavBar = (event) => {
  if (!topNav.contains(event.target)) {
    topNav.classList.remove("responsive");
  }
};
document.addEventListener("click", docClickHandlerNavBar);

let navBar = document.querySelector(".nav-bar#top-nav");

function scrollEvent(scrollPos) {
  let elementAtPageCenter = document.elementFromPoint(window.innerWidth/2,window.innerHeight*2/5);
  let block = elementAtPageCenter;
  if (scrollPos === 0) {
    block = null;
  }
  while (block && !block.classList.contains("content-block")) {
    block = block.parentElement;
  }


  let current = navBar.querySelector(".active");
  if (current != null) {
    current.classList.remove("active");
  }

  if (block) {
    let navID = "nav-" + block.id;
    let activeNavItem = navBar.querySelector("#"+navID);
    activeNavItem.className += " active";
  }
}

// requestAnimationFrame is used to limit the frequency of function calls on every scroll event
let lastScrollPos = 0;
let ticking = false;
window.addEventListener('scroll', function(e) {
  lastScrollPos = window.scrollY;

  if (!ticking) {
    window.requestAnimationFrame(function() {
      scrollEvent(lastScrollPos);
      ticking = false;
    });

    ticking = true;
  }
});
