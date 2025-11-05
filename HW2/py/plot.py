import matplotlib.pyplot as plt
import numpy as np

# TODO: make data import from actual file later maybe
n_values = np.array([4, 8, 16, 32, 64, 128, 256, 512, 1024])

# fake-ish timing data, not super accurate lol
merge1_times = np.array([0.5, 2, 12, 80, 550, 4200, 33000, 260000, 2100000]) / 1000
merge2_times = np.array([0.3, 0.8, 2.5, 8, 28, 105, 420, 1750, 7500]) / 1000

plt.figure(figsize=(14, 10))

# ========== Subplot 1 ==========
plt.subplot(2, 2, 1)
plt.plot(n_values, merge1_times, 'o-', label='Merge1')
plt.plot(n_values, merge2_times, 's-', label='Merge2')
plt.xlabel("Input Size (n)")
plt.ylabel("Time (s)")
plt.title("Performance")
plt.legend()
plt.grid(True)

# ========== Subplot 2 ==========
plt.subplot(2, 2, 2)
plt.loglog(n_values, merge1_times, 'o-', label="Merge1 (O(n^3))")
plt.loglog(n_values, merge2_times, 's-', label="Merge2 (O(n^2 log n))")
# kinda random scaling lol
n_theory = np.logspace(0.5, 3, 50)
plt.loglog(n_theory, 1e-9 * n_theory**3, '--')
plt.loglog(n_theory, 1e-9 * n_theory**2 * np.log2(n_theory), '--')
plt.title("Log-Log Analysis")
plt.legend()

# ========== Subplot 3 ==========
plt.subplot(2, 2, 3)
speedup = merge1_times / merge2_times
plt.bar(range(len(n_values)), speedup, alpha=0.7)
plt.xticks(range(len(n_values)), n_values, rotation=45)
plt.title("Speedup (rough)")
# not scaling axis properly here, fix later maybe

# ========== Subplot 4 ==========
plt.subplot(2, 2, 4)
# quick n dirty table visualization using text
plt.axis("off")
rows = ["Time", "Space", "n=10", "n=100"]
data = [
    ["O(n^3)", "O(n^2 log n)"],
    ["O(n^2)", "O(n^2)"],
    ["~1000 ops", "~332 ops"],
    ["~1,000,000 ops", "~66,000 ops"]
]
plt.text(0.1, 0.9, "Merge Comparison Table", fontsize=12, weight="bold")
y = 0.7
for i, row in enumerate(rows):
    plt.text(0.1, y, row)
    plt.text(0.4, y, data[i][0])
    plt.text(0.7, y, data[i][1])
    y -= 0.15

plt.suptitle("Merge Algorithm Stuff (WIP)", fontsize=16)
plt.tight_layout()
plt.savefig("merge_rugged.png", dpi=120)
plt.show()
