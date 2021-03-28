	.text
	.globl	g_init
	.p2align	1
	.type	g_init,@function
g_init:
.g_init_.0: 
	addi sp, sp, -16
	sw ra, 12(sp)
	li a0, 20
	call malloc
	li a1, 4
	sw a1, 0(a0)
	addi a0, a0, 4
	lui a1, %hi(a)
	sw a0, %lo(a)(a1)
	lw ra, 12(sp)
	addi sp, sp, 16
	ret

	.globl	main
	.p2align	1
	.type	main,@function
main:
.main_.0: 
	addi sp, sp, -16
	sw s0, 4(sp)
	sw s1, 8(sp)
	sw s2, 12(sp)
	sw ra, 0(sp)
	call g_init
	li a0, 20
	call malloc
	li a1, 4
	sw a1, 0(a0)
	addi a0, a0, 4
	mv s1, a0
	mv a0, s1
	lw a1, a
	sw a1, 0(a0)
	addi a0, s1, 4
	lw a1, a
	sw a1, 0(a0)
	addi a0, s1, 8
	lw a1, a
	sw a1, 0(a0)
	addi a0, s1, 12
	lw a1, a
	sw a1, 0(a0)
	mv a0, s1
	addi a0, a0, -4
	lw a0, 0(a0)
	call _gbl_toString
	call _gbl_println
	mv s2, zero
	mv a0, s1
	lw a0, 0(a0)
	addi a0, a0, -4
	lw a0, 0(a0)
	blt s2, a0, .main_.1
	mv s0, zero
	addi a0, s1, 4
	lw a0, 0(a0)
	addi a0, a0, -4
	lw a0, 0(a0)
	blt s0, a0, .main_.2
	la a0, str0
	call _gbl_println
	mv a0, zero
	addi a1, s1, 8
	lw a1, 0(a1)
	addi a1, a1, -4
	lw a1, 0(a1)
	blt a0, a1, .main_.3
	addi a0, s1, 12
	lw a0, 0(a0)
	addi a0, a0, -4
	lw a0, 0(a0)
	blt s0, a0, .main_.4
	mv a0, zero
	lw s0, 4(sp)
	lw s1, 8(sp)
	lw s2, 12(sp)
	lw ra, 0(sp)
	addi sp, sp, 16
	ret
.main_.1: 
	mv a0, s1
	lw a1, 0(a0)
	slli a0, s2, 2
	add a0, a1, a0
	mv s0, a0
	call _gbl_getInt
	sw a0, 0(s0)
	addi a0, s2, 1
	mv s2, a0
	mv a0, s1
	lw a0, 0(a0)
	addi a0, a0, -4
	lw a0, 0(a0)
	blt s2, a0, .main_.1
	mv s0, zero
	addi a0, s1, 4
	lw a0, 0(a0)
	addi a0, a0, -4
	lw a0, 0(a0)
	blt s0, a0, .main_.2
	la a0, str0
	call _gbl_println
	mv a0, zero
	addi a1, s1, 8
	lw a1, 0(a1)
	addi a1, a1, -4
	lw a1, 0(a1)
	blt a0, a1, .main_.3
	addi a0, s1, 12
	lw a0, 0(a0)
	addi a0, a0, -4
	lw a0, 0(a0)
	blt s0, a0, .main_.4
	mv a0, zero
	lw s0, 4(sp)
	lw s1, 8(sp)
	lw s2, 12(sp)
	lw ra, 0(sp)
	addi sp, sp, 16
	ret
.main_.2: 
	addi a0, s1, 4
	lw a1, 0(a0)
	slli a0, s0, 2
	add a0, a1, a0
	lw a0, 0(a0)
	call _gbl_toString
	call _gbl_print
	addi a0, s0, 1
	mv s0, a0
	addi a0, s1, 4
	lw a0, 0(a0)
	addi a0, a0, -4
	lw a0, 0(a0)
	blt s0, a0, .main_.2
	la a0, str0
	call _gbl_println
	mv a0, zero
	addi a1, s1, 8
	lw a1, 0(a1)
	addi a1, a1, -4
	lw a1, 0(a1)
	blt a0, a1, .main_.3
	mv s0, zero
	addi a0, s1, 12
	lw a0, 0(a0)
	addi a0, a0, -4
	lw a0, 0(a0)
	blt s0, a0, .main_.4
	mv a0, zero
	lw s0, 4(sp)
	lw s1, 8(sp)
	lw s2, 12(sp)
	lw ra, 0(sp)
	addi sp, sp, 16
	ret
.main_.3: 
	addi a1, s1, 8
	lw a2, 0(a1)
	slli a1, a0, 2
	add a1, a2, a1
	sw zero, 0(a1)
	addi a0, a0, 1
	addi a1, s1, 8
	lw a1, 0(a1)
	addi a1, a1, -4
	lw a1, 0(a1)
	blt a0, a1, .main_.3
	mv s0, zero
	addi a0, s1, 12
	lw a0, 0(a0)
	addi a0, a0, -4
	lw a0, 0(a0)
	blt s0, a0, .main_.4
	mv a0, zero
	lw s0, 4(sp)
	lw s1, 8(sp)
	lw s2, 12(sp)
	lw ra, 0(sp)
	addi sp, sp, 16
	ret
.main_.4: 
	addi a0, s1, 12
	lw a1, 0(a0)
	slli a0, s0, 2
	add a0, a1, a0
	lw a0, 0(a0)
	call _gbl_toString
	call _gbl_print
	addi a0, s0, 1
	mv s0, a0
	addi a0, s1, 12
	lw a0, 0(a0)
	addi a0, a0, -4
	lw a0, 0(a0)
	blt s0, a0, .main_.4
	mv a0, zero
	lw s0, 4(sp)
	lw s1, 8(sp)
	lw s2, 12(sp)
	lw ra, 0(sp)
	addi sp, sp, 16
	ret

	.type	a,@object
	.section	.data
	.globl	a
	.p2align	2
a:
	.zero	4
	.size	a, 4

	.type	str0,@object
	.section	.rodata
str0:
	.asciz	""
	.size	str0, 1

