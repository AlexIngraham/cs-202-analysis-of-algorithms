import matplotlib.pyplot as plt

# Performance data (example, replace with your actual results)
n_values = [1000, 5000, 10000, 50000, 100000, 250000, 500000, 750000, 1000000]
select1_times = [0, 2, 4, 25, 55, 150, 320, 490, 680]  # Sorting
select2_times = [0, 0, 1, 3, 6, 15, 30, 45, 60]        # QuickSelect

# Make a simple plot
plt.figure(figsize=(8, 6))

plt.plot(n_values, select1_times, 'bo-', label='Select1 (Sorting)')
plt.plot(n_values, select2_times, 'rs-', label='Select2 (QuickSelect)')

plt.xlabel("Input Size (n)")
plt.ylabel("Runtime (ms)")
plt.title("Runtime Comparison of Select1 vs Select2")
plt.legend()
plt.grid(True)

plt.show()
