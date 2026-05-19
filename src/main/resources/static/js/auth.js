const particlePalettes = {
    petal: ["#f7d0e1", "#f9bfd8", "#ffd8e9", "#f8bfd6"],
    leaf: ["#48f3a4", "#ffbe14", "#17a53e", "#d16d5a"]
};

document.querySelectorAll("[data-particle-theme]").forEach((container) => {
    const theme = container.dataset.particleTheme;
    const colors = particlePalettes[theme] || particlePalettes.petal;
    const count = theme === "leaf" ? 8 : 7;

    for (let index = 0; index < count; index += 1) {
        const particle = document.createElement("span");
        const size = 14 + Math.random() * 20;

        particle.className = `float-particle float-particle--${theme}`;
        particle.style.width = `${size}px`;
        particle.style.height = `${Math.max(size * 0.55, 10)}px`;
        particle.style.left = `${Math.random() * 92}%`;
        particle.style.top = `${Math.random() * 86}%`;
        particle.style.background = colors[index % colors.length];
        particle.style.animationDelay = `${Math.random() * 4}s`;
        particle.style.animationDuration = `${9 + Math.random() * 5}s`;
        particle.style.transform = `rotate(${Math.random() * 180}deg)`;

        container.appendChild(particle);
    }
});
