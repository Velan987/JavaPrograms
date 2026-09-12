# 1. Recursion
 function calls itself to solve smaller subproblems.
Break a problem into **smaller identical problems**

### 🔹 Example (Fibonacci)

```
fib(n) = fib(n-1) + fib(n-2)
```

### 🔹 Problem with recursion

* Recomputes the same subproblems again and again
* Time complexity becomes **exponential**

👉 Example:

```
fib(5)
 ├── fib(4)
 │    ├── fib(3)
 │    │    ├── fib(2)
 │    │    └── fib(1)
 │    └── fib(2)
 └── fib(3)   <-- repeated!
```

### 🔴 Issue

This is called **overlapping subproblems**

---

# 2. Memoization (Top-Down Optimization)

### 🔹 What it is

Memoization = **Recursion + caching**

You store results of subproblems so you don’t recompute them.

### 🔹 Key idea

“Compute once, reuse forever”

### 🔹 Example

```
memo = {}

fib(n):
  if n in memo:
    return memo[n]

  if n <= 1:
    return n

  memo[n] = fib(n-1) + fib(n-2)
  return memo[n]
```

### 🔹 What changed?

* Same recursive structure
* But avoids recomputation

### 🔹 Complexity

* Time: **O(n)**
* Space: **O(n)** (cache + recursion stack)

### 🟢 Insight

Memoization is just **optimized recursion**

---

# 3. Dynamic Programming (Bottom-Up Approach)

### 🔹 What it is

Dynamic Programming (DP) solves problems **iteratively**, building from smallest subproblems.

### 🔹 Key idea

“Start from base cases and build up”

### 🔹 Example

```
fib(n):
  dp[0] = 0
  dp[1] = 1

  for i from 2 to n:
    dp[i] = dp[i-1] + dp[i-2]

  return dp[n]
```

### 🔹 Complexity

* Time: **O(n)**
* Space: **O(n)** (can be optimized to O(1))

---

# 🔥 Key Differences (This is the core)

| Aspect         | Recursion  | Memoization      | Dynamic Programming |
| -------------- | ---------- | ---------------- | ------------------- |
| Approach       | Top-down   | Top-down         | Bottom-up           |
| Recomputations | Yes ❌      | No ✅             | No ✅                |
| Speed          | Slow (exp) | Fast (linear)    | Fast (linear)       |
| Implementation | Simple     | Slightly complex | More structured     |
| Stack usage    | High       | High             | None                |

---

# 🧠 How They Relate (Very Important)

Think of them as evolution:

```
Recursion
   ↓ (add caching)
Memoization
   ↓ (convert to iteration)
Dynamic Programming
```

---

# 🚀 Practical Example Mapping

| Problem                    | Technique           |
| -------------------------- | ------------------- |
| Tree traversal             | Recursion           |
| Fibonacci (optimized)      | Memoization         |
| Knapsack                   | Dynamic Programming |
| Longest Common Subsequence | Dynamic Programming |

---


# 🚀 Problem: Fibonacci (classic but perfect to learn)

```text
fib(n) = fib(n-1) + fib(n-2)
```

---

# 1️⃣ Step 1: Pure Recursion (Brute Force)

### ✅ Code

```java
public class Fibonacci {

    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(fib(5)); // 5
    }
}
```

### ❌ Problem

* Repeats work
* Time complexity: **O(2ⁿ)**

### 🔥 Mental Model

You're solving a tree of subproblems:

* Same values recomputed multiple times

---

# 2️⃣ Step 2: Add Memoization (Top-Down DP)

👉 Ask yourself:

> “Am I solving the same subproblem again?”

Yes → cache it.

---

### ✅ Code (Memoization)

```java
public class Fibonacci {

    public static int fib(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }

        if (memo[n] != -1) {   // already computed
            return memo[n];
        }

        memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
        return memo[n];
    }

    public static void main(String[] args) {
        int n = 5;
        int[] memo = new int[n + 1]; //or we can use hashmap

        // initialize with -1
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }

        System.out.println(fib(n, memo)); // 5
    }
}
```

---

### 🔥 What changed?

* Added a **cache (memo)**
* Before computing → check if already solved

---

### ⏱ Complexity

* Time: **O(n)**
* Space: **O(n)**

---

### 🧠 Key Insight

You didn’t change the logic—just avoided recomputation.

---

# 3️⃣ Step 3: Convert to Bottom-Up DP

👉 Now ask:

> “Instead of going top-down, can I build from smallest values?”

Yes.

---

### ✅ Code (DP array)

```java
public class Fibonacci {

    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(fib(5)); // 5
    }
}
```

---

### 🔥 What changed?

* No recursion
* Build answers **iteratively** 0 to n

---

### 🧠 Key Insight

You reversed the thinking:

* Memoization: “solve when needed”
* DP: “solve everything in order”

---

# 4️⃣ Step 4: Optimize Space (Advanced DP)

👉 Observe:
We only use last 2 values.

---

### ✅ Code (O(1) space)

```java
public class Fibonacci {

    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }

        int prev2 = 0;
        int prev1 = 1;

        for (int i = 2; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    public static void main(String[] args) {
        System.out.println(fib(5)); // 5
    }
}
```

---

# 🔥 Full Transformation Summary

| Step | Idea         | Code Style       | Complexity |
| ---- | ------------ | ---------------- | ---------- |
| 1    | Recursion    | Top-down         | O(2ⁿ)      |
| 2    | Memoization  | Top-down + cache | O(n)       |
| 3    | DP           | Bottom-up        | O(n)       |
| 4    | Optimized DP | Bottom-up        | O(1) space |

---

# 🧠 The Real Skill (VERY IMPORTANT)

Whenever you see recursion:

### Step-by-step thinking:

### ✅ Step A: Identify state

```text
fib(n)
```

---

### ✅ Step B: Identify transition

```text
fib(n) = fib(n-1) + fib(n-2)
```

---

### ✅ Step C: Add memo

```text
cache[n]
```

---

### ✅ Step D: Convert to DP

```text
dp[i] = dp[i-1] + dp[i-2]
```

---
