import matplotlib.pyplot as plt
import subprocess
import re

def run_java_and_parse():
    """Run the Java program and parse the output"""
    # Compile and run Java program
    subprocess.run(['javac', 'main.java'], check=True)
    result = subprocess.run(['java', 'main'], 
                          capture_output=True, text=True, check=True)
    
    # Parse output
    sizes = []
    time_n2 = []
    time_nlogn = []
    
    for line in result.stdout.split('\n'):
        # Look for lines with Size: and timing data
        match = re.search(r'Size: (\d+).*O\(n\^2\): ([\d.E-]+) ms.*O\(n log n\): ([\d.E-]+) ms', line)
        if match:
            sizes.append(int(match.group(1)))
            time_n2.append(float(match.group(2)))
            time_nlogn.append(float(match.group(3)))
    
    return sizes, time_n2, time_nlogn

def plot_results(sizes, time_n2, time_nlogn):
    """Create plots comparing the two algorithms"""
    plt.figure(figsize=(14, 5))
    
    # Plot 1: Running times on same graph
    plt.subplot(1, 2, 1)
    plt.plot(sizes, time_n2, 'b-o', label='O(n²) Algorithm', linewidth=2, markersize=8)
    plt.plot(sizes, time_nlogn, 'r-s', label='O(n log n) Algorithm', linewidth=2, markersize=8)
    plt.xlabel('Input Size (n)', fontsize=12)
    plt.ylabel('Running Time (ms)', fontsize=12)
    plt.title('Running Time Comparison', fontsize=14, fontweight='bold')
    plt.legend(fontsize=11)
    plt.grid(True, alpha=0.3)
    
    # Plot 2: Speedup factor
    plt.subplot(1, 2, 2)
    speedup = [n2/nlogn for n2, nlogn in zip(time_n2, time_nlogn)]
    plt.plot(sizes, speedup, 'g-^', linewidth=2, markersize=8)
    plt.xlabel('Input Size (n)', fontsize=12)
    plt.ylabel('Speedup Factor (O(n²) / O(n log n))', fontsize=12)
    plt.title('Performance Speedup', fontsize=14, fontweight='bold')
    plt.grid(True, alpha=0.3)
    
    plt.tight_layout()
    plt.savefig('lis_comparison.png', dpi=300, bbox_inches='tight')
    print("Plot saved as 'lis_comparison.png'")
    plt.show()

def manual_input_mode():
    """Allow manual input of data if Java program isn't available"""
    print("Manual data entry mode")
    print("Enter data from your Java program output:")
    print("Format: size,time_n2,time_nlogn (one per line)")
    print("Enter 'done' when finished")
    
    sizes = []
    time_n2 = []
    time_nlogn = []
    
    while True:
        line = input("> ").strip()
        if line.lower() == 'done':
            break
        try:
            s, t1, t2 = line.split(',')
            sizes.append(int(s))
            time_n2.append(float(t1))
            time_nlogn.append(float(t2))
        except:
            print("Invalid format. Use: size,time_n2,time_nlogn")
    
    return sizes, time_n2, time_nlogn

if __name__ == '__main__':
    try:
        print("Running Java program and collecting data...")
        sizes, time_n2, time_nlogn = run_java_and_parse()
    except Exception as e:
        print(f"Could not run Java program automatically: {e}")
        print("Switching to manual input mode...\n")
        sizes, time_n2, time_nlogn = manual_input_mode()
    
    if sizes:
        plot_results(sizes, time_n2, time_nlogn)
        
        # Print summary statistics
        print("\nSummary Statistics:")
        print(f"Average O(n²) time: {sum(time_n2)/len(time_n2):.2f} ms")
        print(f"Average O(n log n) time: {sum(time_nlogn)/len(time_nlogn):.2f} ms")
        print(f"Average speedup: {sum(t1/t2 for t1,t2 in zip(time_n2, time_nlogn))/len(time_n2):.2f}x")
    else:
        print("No data to plot!")
