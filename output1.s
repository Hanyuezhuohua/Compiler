	.text
	.globl	g_init
	.p2align	1
	.type	g_init,@function
g_init:
.g_init_.0: 
	addi sp, sp, 0
	mv %0, s0
	mv %1, s1
	mv %2, s2
	mv %3, s3
	mv %4, s4
	mv %5, s5
	mv %6, s6
	mv %7, s7
	mv %8, s8
	mv %9, s9
	mv %10, s10
	mv %11, s11
	mv %12, ra
	mv s0, %0
	mv s1, %1
	mv s2, %2
	mv s3, %3
	mv s4, %4
	mv s5, %5
	mv s6, %6
	mv s7, %7
	mv s8, %8
	mv s9, %9
	mv s10, %10
	mv s11, %11
	mv ra, %12
	addi sp, sp, 0
	ret

	.globl	main
	.p2align	1
	.type	main,@function
main:
.main_.0: 
	addi sp, sp, 0
	mv %13, s0
	mv %14, s1
	mv %15, s2
	mv %16, s3
	mv %17, s4
	mv %18, s5
	mv %19, s6
	mv %20, s7
	mv %21, s8
	mv %22, s9
	mv %23, s10
	mv %24, s11
	mv %25, ra
	call g_init
	la %26, str0
	mv %27, %26
	la %28, str1
	mv %29, %28
	mv a0, %27
	mv a1, %29
	call _str_concat
	mv %30, a0
	mv a0, %30
	call _str_length
	mv %31, a0
	mv a0, %30
	li %32, 5
	mv a1, %32
	call _str_ord
	mv %33, a0
	add %34, %31, %33
	mv a0, %34
	mv s0, %13
	mv s1, %14
	mv s2, %15
	mv s3, %16
	mv s4, %17
	mv s5, %18
	mv s6, %19
	mv s7, %20
	mv s8, %21
	mv s9, %22
	mv s10, %23
	mv s11, %24
	mv ra, %25
	addi sp, sp, 0
	ret

	.type	str0,@object
	.section	.rodata
str0:
	.asciz	"aaa"
	.size	str0, 4

	.type	str1,@object
	.section	.rodata
str1:
	.asciz	"bbbbb"
	.size	str1, 6

