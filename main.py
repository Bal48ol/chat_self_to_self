import numpy as np

# Определение матрицы A и вектора b
A = np.array([[4, -1, 2], [1, 5, -2], [-1, 1, -4]])
b = np.array([7, 2, -8])

# Проверка положительной определенности и симметричности матрицы A
if np.all(np.linalg.eigvals(A) > 0) and np.array_equal(A, A.T):
    # Метод минимальных невязок
    x = np.zeros_like(b)
    r = b - np.dot(A, x)
    p = r
    
    print("Матрица A:")
    for row in A:
        for elem in row:
            print(f"{elem}", end=" ")
        print()
    print()
    
    print("Вектор b:")
    for elem in b:
        print(f"{elem}")
    print()
    
    for i in range(len(b)):
        alpha = np.dot(r, r) / np.dot(p, np.dot(A, p))
        x = x + alpha * p
        r_new = r - alpha * np.dot(A, p)
        beta = np.dot(r_new, r_new) / np.dot(r, r)
        p = r_new + beta * p
        r = r_new
        
        print(f"Итерация {i+1}: {x}")

    print("Решение системы уравнений:", np.round(x))
    
else:
    print("Матрица A:")
    for row in A:
        for elem in row:
            print(f"{elem}", end=" ")
        print()
    print()
    
    print("Матрица A не является положительно определенной и/или симметричной.")
