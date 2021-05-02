	.text
	.globl	init
	.p2align	1
	.type	init,@function
init:
.init_b.0: 
	addi sp, sp, -32
	sw s0, 16(sp)
	sw s1, 20(sp)
	sw s2, 24(sp)
	sw s3, 28(sp)
	sw ra, 12(sp)
	lw s0, n
	addi s1, s0, 1
	li a0, 4
	mul a0, s1, a0
	addi s2, a0, 4
	mv a0, s2
	call malloc
	sw s1, 0(a0)
	addi a0, a0, 4
	mv s3, a0
	mv a0, s2
	call malloc
	sw s1, 0(a0)
	addi a2, a0, 4
	lui a0, %hi(first)
	sw s3, %lo(first)(a0)
	lui a0, %hi(depth)
	sw a2, %lo(depth)(a0)
	mv a1, zero
.init_b.1: 
	ble a1, s0, .init_b.10
	lw a0, m
	addi s0, a0, 1
	li a0, 4
	mul a0, s0, a0
	addi a0, a0, 4
	call malloc
	sw s0, 0(a0)
	addi a0, a0, 4
	mv s0, a0
	lw a0, n
	addi s1, a0, 1
	li a0, 4
	mul a0, s1, a0
	addi a0, a0, 4
	call malloc
	sw s1, 0(a0)
	addi a0, a0, 4
	mv s2, a0
	lui a0, %hi(ans)
	sw s0, %lo(ans)(a0)
	mv s3, zero
.init_b.2: 
	blt s3, s1, .init_b.9
	lw a4, n
	lui a0, %hi(f)
	sw s2, %lo(f)(a0)
	mv a0, zero
.init_b.3: 
	ble a0, a4, .init_b.6
	li a0, 88
	call malloc
	li a1, 21
	sw a1, 0(a0)
	addi a3, a0, 4
	li a0, 1
	sw a0, 0(a3)
	lui a0, %hi(pow)
	sw a3, %lo(pow)(a0)
	li a2, 1
.init_b.4: 
	li a0, 20
	ble a2, a0, .init_b.5
	lw a1, n
	li a0, 2
	mul a0, a0, a1
	addi s0, a0, -1
	li a0, 4
	mul a0, s0, a0
	addi a0, a0, 4
	call malloc
	sw s0, 0(a0)
	addi a0, a0, 4
	lui a1, %hi(edge)
	sw a0, %lo(edge)(a1)
	lw s0, 16(sp)
	lw s1, 20(sp)
	lw s2, 24(sp)
	lw s3, 28(sp)
	lw ra, 12(sp)
	addi sp, sp, 32
	ret
.init_b.5: 
	li a0, 4
	mul a0, a2, a0
	add a0, a3, a0
	addi a4, a2, -1
	li a1, 4
	mul a1, a4, a1
	add a1, a3, a1
	lw a4, 0(a1)
	li a1, 2
	mul a1, a1, a4
	sw a1, 0(a0)
	addi a2, a2, 1
	j .init_b.4
.init_b.6: 
	li a1, 4
	mul a1, a0, a1
	add a1, s2, a1
	lw a3, 0(a1)
	mv a2, zero
.init_b.7: 
	li a1, 20
	ble a2, a1, .init_b.8
	addi a0, a0, 1
	j .init_b.3
.init_b.8: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	sw zero, 0(a1)
	addi a2, a2, 1
	j .init_b.7
.init_b.9: 
	li a0, 4
	mul a0, s3, a0
	add a0, s2, a0
	mv s0, a0
	li a0, 88
	call malloc
	li a1, 21
	sw a1, 0(a0)
	addi a0, a0, 4
	sw a0, 0(s0)
	addi a0, s3, 1
	mv s3, a0
	j .init_b.2
.init_b.10: 
	li a0, 4
	mul a0, a1, a0
	add a0, s3, a0
	sw zero, 0(a0)
	li a0, 4
	mul a0, a1, a0
	add a0, a2, a0
	sw zero, 0(a0)
	addi a1, a1, 1
	j .init_b.1

	.globl	addedge
	.p2align	1
	.type	addedge,@function
addedge:
.addedge_b.0: 
	addi sp, sp, -32
	sw s0, 16(sp)
	sw s1, 20(sp)
	sw s2, 24(sp)
	sw s3, 28(sp)
	sw ra, 12(sp)
	mv s0, a0
	mv s1, a1
	lw a0, total
	addi s2, a0, 1
	lw a1, edge
	li a0, 4
	mul a0, s2, a0
	add a0, a1, a0
	mv s3, a0
	li a0, 12
	call malloc
	sw a0, 0(s3)
	sw s0, 0(a0)
	addi a1, a0, 4
	sw s1, 0(a1)
	addi a0, a0, 8
	lw a2, first
	li a1, 4
	mul a1, s0, a1
	add a1, a2, a1
	lw a2, 0(a1)
	sw a2, 0(a0)
	sw s2, 0(a1)
	lui a0, %hi(total)
	sw s2, %lo(total)(a0)
	lw s0, 16(sp)
	lw s1, 20(sp)
	lw s2, 24(sp)
	lw s3, 28(sp)
	lw ra, 12(sp)
	addi sp, sp, 32
	ret

	.globl	dfs
	.p2align	1
	.type	dfs,@function
dfs:
.dfs_b.0: 
	addi sp, sp, -80
	sw s0, 12(sp)
	sw s1, 16(sp)
	sw s2, 20(sp)
	sw s3, 24(sp)
	sw s4, 28(sp)
	sw s5, 32(sp)
	sw s6, 36(sp)
	sw s7, 40(sp)
	sw s8, 44(sp)
	sw s9, 48(sp)
	sw s10, 52(sp)
	sw s11, 56(sp)
	sw ra, 8(sp)
	sw a0, 68(sp)
	sw a1, 60(sp)
	lw a3, depth
	li a1, 4
	mul a0, a0, a1
	add a0, a3, a0
	li a2, 4
	lw a1, 60(sp)
	mul a1, a1, a2
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 1
	sw a1, 0(a0)
	lw a4, f
	li a1, 4
	lw a0, 68(sp)
	mul a0, a0, a1
	add a0, a4, a0
	lw a3, 0(a0)
	lw a0, 60(sp)
	sw a0, 0(a3)
	li a2, 1
.dfs_b.1: 
	li a0, 20
	ble a2, a0, .dfs_b.48
	lw a2, first
	li a1, 4
	lw a0, 68(sp)
	mul a0, a0, a1
	add a0, a2, a0
	lw a0, 0(a0)
	mv s9, a0
.dfs_b.2: 
	bne s9, zero, .dfs_b.3
	lw s0, 12(sp)
	lw s1, 16(sp)
	lw s2, 20(sp)
	lw s3, 24(sp)
	lw s4, 28(sp)
	lw s5, 32(sp)
	lw s6, 36(sp)
	lw s7, 40(sp)
	lw s8, 44(sp)
	lw s9, 48(sp)
	lw s10, 52(sp)
	lw s11, 56(sp)
	lw ra, 8(sp)
	addi sp, sp, 80
	ret
.dfs_b.3: 
	lw a1, edge
	li a0, 4
	mul a0, s9, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 4
	lw a0, 0(a0)
	sw a0, 72(sp)
	lw a1, 72(sp)
	lw a0, 60(sp)
	bne a1, a0, .dfs_b.5
.dfs_b.4: 
	lw a1, edge
	li a0, 4
	mul a0, s9, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 8
	lw a0, 0(a0)
	mv s9, a0
	j .dfs_b.2
.dfs_b.5: 
	lw a3, depth
	li a1, 4
	lw a0, 72(sp)
	mul a0, a0, a1
	add a0, a3, a0
	li a2, 4
	lw a1, 68(sp)
	mul a1, a1, a2
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 1
	sw a1, 0(a0)
	lw a4, f
	li a1, 4
	lw a0, 72(sp)
	mul a0, a0, a1
	add a0, a4, a0
	lw a3, 0(a0)
	lw a0, 68(sp)
	sw a0, 0(a3)
	li a2, 1
.dfs_b.6: 
	li a0, 20
	ble a2, a0, .dfs_b.47
	lw a2, first
	li a1, 4
	lw a0, 72(sp)
	mul a0, a0, a1
	add a0, a2, a0
	lw a0, 0(a0)
	mv s6, a0
.dfs_b.7: 
	bne s6, zero, .dfs_b.8
	j .dfs_b.4
.dfs_b.8: 
	lw a1, edge
	li a0, 4
	mul a0, s6, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 4
	lw a0, 0(a0)
	sw a0, 76(sp)
	lw a1, 76(sp)
	lw a0, 68(sp)
	bne a1, a0, .dfs_b.10
.dfs_b.9: 
	lw a1, edge
	li a0, 4
	mul a0, s6, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 8
	lw a0, 0(a0)
	mv s6, a0
	j .dfs_b.7
.dfs_b.10: 
	lw a3, depth
	li a1, 4
	lw a0, 76(sp)
	mul a0, a0, a1
	add a0, a3, a0
	li a2, 4
	lw a1, 72(sp)
	mul a1, a1, a2
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 1
	sw a1, 0(a0)
	lw a4, f
	li a1, 4
	lw a0, 76(sp)
	mul a0, a0, a1
	add a0, a4, a0
	lw a3, 0(a0)
	lw a0, 72(sp)
	sw a0, 0(a3)
	li a2, 1
.dfs_b.11: 
	li a0, 20
	ble a2, a0, .dfs_b.46
	lw a2, first
	li a1, 4
	lw a0, 76(sp)
	mul a0, a0, a1
	add a0, a2, a0
	lw a0, 0(a0)
	mv s8, a0
.dfs_b.12: 
	bne s8, zero, .dfs_b.13
	j .dfs_b.9
.dfs_b.13: 
	lw a1, edge
	li a0, 4
	mul a0, s8, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 4
	lw s11, 0(a0)
	lw a0, 72(sp)
	bne s11, a0, .dfs_b.15
.dfs_b.14: 
	lw a1, edge
	li a0, 4
	mul a0, s8, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 8
	lw a0, 0(a0)
	mv s8, a0
	j .dfs_b.12
.dfs_b.15: 
	lw a3, depth
	li a0, 4
	mul a0, s11, a0
	add a0, a3, a0
	li a2, 4
	lw a1, 76(sp)
	mul a1, a1, a2
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 1
	sw a1, 0(a0)
	lw a4, f
	li a0, 4
	mul a0, s11, a0
	add a0, a4, a0
	lw a3, 0(a0)
	lw a0, 76(sp)
	sw a0, 0(a3)
	li a2, 1
.dfs_b.16: 
	li a0, 20
	ble a2, a0, .dfs_b.45
	lw a1, first
	li a0, 4
	mul a0, s11, a0
	add a0, a1, a0
	lw a0, 0(a0)
	mv s7, a0
.dfs_b.17: 
	bne s7, zero, .dfs_b.18
	j .dfs_b.14
.dfs_b.18: 
	lw a1, edge
	li a0, 4
	mul a0, s7, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 4
	lw s10, 0(a0)
	lw a0, 76(sp)
	bne s10, a0, .dfs_b.20
.dfs_b.19: 
	lw a1, edge
	li a0, 4
	mul a0, s7, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 8
	lw a0, 0(a0)
	mv s7, a0
	j .dfs_b.17
.dfs_b.20: 
	lw a2, depth
	li a0, 4
	mul a0, s10, a0
	add a0, a2, a0
	li a1, 4
	mul a1, s11, a1
	add a1, a2, a1
	lw a1, 0(a1)
	addi a1, a1, 1
	sw a1, 0(a0)
	lw a4, f
	li a0, 4
	mul a0, s10, a0
	add a0, a4, a0
	lw a3, 0(a0)
	sw s11, 0(a3)
	li a2, 1
.dfs_b.21: 
	li a0, 20
	ble a2, a0, .dfs_b.44
	lw a1, first
	li a0, 4
	mul a0, s10, a0
	add a0, a1, a0
	lw a0, 0(a0)
	mv s5, a0
.dfs_b.22: 
	bne s5, zero, .dfs_b.23
	j .dfs_b.19
.dfs_b.23: 
	lw a1, edge
	li a0, 4
	mul a0, s5, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 4
	lw s4, 0(a0)
	bne s4, s11, .dfs_b.25
.dfs_b.24: 
	lw a1, edge
	li a0, 4
	mul a0, s5, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 8
	lw a0, 0(a0)
	mv s5, a0
	j .dfs_b.22
.dfs_b.25: 
	lw a2, depth
	li a0, 4
	mul a0, s4, a0
	add a0, a2, a0
	li a1, 4
	mul a1, s10, a1
	add a1, a2, a1
	lw a1, 0(a1)
	addi a1, a1, 1
	sw a1, 0(a0)
	lw a4, f
	li a0, 4
	mul a0, s4, a0
	add a0, a4, a0
	lw a3, 0(a0)
	sw s10, 0(a3)
	li a2, 1
.dfs_b.26: 
	li a0, 20
	ble a2, a0, .dfs_b.43
	lw a1, first
	li a0, 4
	mul a0, s4, a0
	add a0, a1, a0
	lw a0, 0(a0)
	mv s3, a0
.dfs_b.27: 
	bne s3, zero, .dfs_b.28
	j .dfs_b.24
.dfs_b.28: 
	lw a1, edge
	li a0, 4
	mul a0, s3, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 4
	lw s2, 0(a0)
	bne s2, s10, .dfs_b.30
.dfs_b.29: 
	lw a1, edge
	li a0, 4
	mul a0, s3, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 8
	lw a0, 0(a0)
	mv s3, a0
	j .dfs_b.27
.dfs_b.30: 
	lw a2, depth
	li a0, 4
	mul a0, s2, a0
	add a0, a2, a0
	li a1, 4
	mul a1, s4, a1
	add a1, a2, a1
	lw a1, 0(a1)
	addi a1, a1, 1
	sw a1, 0(a0)
	lw a4, f
	li a0, 4
	mul a0, s2, a0
	add a0, a4, a0
	lw a3, 0(a0)
	sw s4, 0(a3)
	li a2, 1
.dfs_b.31: 
	li a0, 20
	ble a2, a0, .dfs_b.42
	lw a1, first
	li a0, 4
	mul a0, s2, a0
	add a0, a1, a0
	lw a0, 0(a0)
	mv s1, a0
.dfs_b.32: 
	bne s1, zero, .dfs_b.33
	j .dfs_b.29
.dfs_b.33: 
	lw a1, edge
	li a0, 4
	mul a0, s1, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 4
	lw a0, 0(a0)
	sw a0, 64(sp)
	bne a0, s4, .dfs_b.35
.dfs_b.34: 
	lw a1, edge
	li a0, 4
	mul a0, s1, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 8
	lw a0, 0(a0)
	mv s1, a0
	j .dfs_b.32
.dfs_b.35: 
	lw a2, depth
	li a1, 4
	lw a0, 64(sp)
	mul a0, a0, a1
	add a0, a2, a0
	mul a1, s2, a1
	add a1, a2, a1
	lw a1, 0(a1)
	addi a1, a1, 1
	sw a1, 0(a0)
	lw a4, f
	li a1, 4
	lw a0, 64(sp)
	mul a0, a0, a1
	add a0, a4, a0
	lw a3, 0(a0)
	sw s2, 0(a3)
	li a2, 1
.dfs_b.36: 
	li a0, 20
	ble a2, a0, .dfs_b.41
	lw a2, first
	li a1, 4
	lw a0, 64(sp)
	mul a0, a0, a1
	add a0, a2, a0
	lw a0, 0(a0)
	mv s0, a0
.dfs_b.37: 
	bne s0, zero, .dfs_b.38
	j .dfs_b.34
.dfs_b.38: 
	lw a1, edge
	li a0, 4
	mul a0, s0, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 4
	lw a0, 0(a0)
	bne a0, s2, .dfs_b.40
.dfs_b.39: 
	lw a1, edge
	li a0, 4
	mul a0, s0, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 8
	lw a0, 0(a0)
	mv s0, a0
	j .dfs_b.37
.dfs_b.40: 
	lw a1, 64(sp)
	call dfs
	j .dfs_b.39
.dfs_b.41: 
	li a0, 4
	mul a0, a2, a0
	add a0, a3, a0
	addi a5, a2, -1
	li a1, 4
	mul a1, a5, a1
	add a1, a3, a1
	lw a6, 0(a1)
	li a1, 4
	mul a1, a6, a1
	add a1, a4, a1
	lw a6, 0(a1)
	li a1, 4
	mul a1, a5, a1
	add a1, a6, a1
	lw a1, 0(a1)
	sw a1, 0(a0)
	addi a2, a2, 1
	j .dfs_b.36
.dfs_b.42: 
	li a0, 4
	mul a0, a2, a0
	add a0, a3, a0
	addi a6, a2, -1
	li a1, 4
	mul a1, a6, a1
	add a1, a3, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a5, a1
	add a1, a4, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a6, a1
	add a1, a5, a1
	lw a1, 0(a1)
	sw a1, 0(a0)
	addi a2, a2, 1
	j .dfs_b.31
.dfs_b.43: 
	li a0, 4
	mul a0, a2, a0
	add a0, a3, a0
	addi a6, a2, -1
	li a1, 4
	mul a1, a6, a1
	add a1, a3, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a5, a1
	add a1, a4, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a6, a1
	add a1, a5, a1
	lw a1, 0(a1)
	sw a1, 0(a0)
	addi a2, a2, 1
	j .dfs_b.26
.dfs_b.44: 
	li a0, 4
	mul a0, a2, a0
	add a0, a3, a0
	addi a6, a2, -1
	li a1, 4
	mul a1, a6, a1
	add a1, a3, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a5, a1
	add a1, a4, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a6, a1
	add a1, a5, a1
	lw a1, 0(a1)
	sw a1, 0(a0)
	addi a2, a2, 1
	j .dfs_b.21
.dfs_b.45: 
	li a0, 4
	mul a0, a2, a0
	add a0, a3, a0
	addi a6, a2, -1
	li a1, 4
	mul a1, a6, a1
	add a1, a3, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a5, a1
	add a1, a4, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a6, a1
	add a1, a5, a1
	lw a1, 0(a1)
	sw a1, 0(a0)
	addi a2, a2, 1
	j .dfs_b.16
.dfs_b.46: 
	li a0, 4
	mul a0, a2, a0
	add a0, a3, a0
	addi a6, a2, -1
	li a1, 4
	mul a1, a6, a1
	add a1, a3, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a5, a1
	add a1, a4, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a6, a1
	add a1, a5, a1
	lw a1, 0(a1)
	sw a1, 0(a0)
	addi a2, a2, 1
	j .dfs_b.11
.dfs_b.47: 
	li a0, 4
	mul a0, a2, a0
	add a0, a3, a0
	addi a6, a2, -1
	li a1, 4
	mul a1, a6, a1
	add a1, a3, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a5, a1
	add a1, a4, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a6, a1
	add a1, a5, a1
	lw a1, 0(a1)
	sw a1, 0(a0)
	addi a2, a2, 1
	j .dfs_b.6
.dfs_b.48: 
	li a0, 4
	mul a0, a2, a0
	add a0, a3, a0
	addi a6, a2, -1
	li a1, 4
	mul a1, a6, a1
	add a1, a3, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a5, a1
	add a1, a4, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a6, a1
	add a1, a5, a1
	lw a1, 0(a1)
	sw a1, 0(a0)
	addi a2, a2, 1
	j .dfs_b.1

	.globl	lca
	.p2align	1
	.type	lca,@function
lca:
.lca_b.0: 
	lw a3, f
	lw a5, pow
	lw a4, depth
	li a2, 4
	mul a2, a0, a2
	add a2, a4, a2
	li a6, 4
	mul a6, a1, a6
	add a6, a4, a6
	lw a7, 0(a2)
	lw a2, 0(a6)
	blt a7, a2, .lca_b.88
	mv a2, a0
	mv a0, a1
.lca_b.1: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	li a6, 4
	mul a6, a0, a6
	add a6, a4, a6
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a7, a7, a1
	addi a1, a5, 80
	lw a1, 0(a1)
	bge a7, a1, .lca_b.87
.lca_b.2: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a7, a7, a1
	addi a1, a5, 76
	lw a1, 0(a1)
	bge a7, a1, .lca_b.86
.lca_b.3: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a7, a7, a1
	addi a1, a5, 72
	lw a1, 0(a1)
	bge a7, a1, .lca_b.85
.lca_b.4: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a6, a7, a1
	addi a1, a5, 68
	lw a1, 0(a1)
	bge a6, a1, .lca_b.84
.lca_b.5: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	li a6, 4
	mul a6, a0, a6
	add a6, a4, a6
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a7, a7, a1
	addi a1, a5, 64
	lw a1, 0(a1)
	bge a7, a1, .lca_b.83
.lca_b.6: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a7, a7, a1
	addi a1, a5, 60
	lw a1, 0(a1)
	bge a7, a1, .lca_b.82
.lca_b.7: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a7, a7, a1
	addi a1, a5, 56
	lw a1, 0(a1)
	bge a7, a1, .lca_b.81
.lca_b.8: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a6, a7, a1
	addi a1, a5, 52
	lw a1, 0(a1)
	bge a6, a1, .lca_b.80
.lca_b.9: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	li a6, 4
	mul a6, a0, a6
	add a6, a4, a6
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a7, a7, a1
	addi a1, a5, 48
	lw a1, 0(a1)
	bge a7, a1, .lca_b.79
.lca_b.10: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a7, a7, a1
	addi a1, a5, 44
	lw a1, 0(a1)
	bge a7, a1, .lca_b.78
.lca_b.11: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a7, a7, a1
	addi a1, a5, 40
	lw a1, 0(a1)
	bge a7, a1, .lca_b.77
.lca_b.12: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a6, a7, a1
	addi a1, a5, 36
	lw a1, 0(a1)
	bge a6, a1, .lca_b.76
.lca_b.13: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	li a6, 4
	mul a6, a0, a6
	add a6, a4, a6
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a7, a7, a1
	addi a1, a5, 32
	lw a1, 0(a1)
	bge a7, a1, .lca_b.75
.lca_b.14: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a7, a7, a1
	addi a1, a5, 28
	lw a1, 0(a1)
	bge a7, a1, .lca_b.74
.lca_b.15: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a7, a7, a1
	addi a1, a5, 24
	lw a1, 0(a1)
	bge a7, a1, .lca_b.73
.lca_b.16: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a6, a7, a1
	addi a1, a5, 20
	lw a1, 0(a1)
	bge a6, a1, .lca_b.72
.lca_b.17: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	li a6, 4
	mul a6, a0, a6
	add a6, a4, a6
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a7, a7, a1
	addi a1, a5, 16
	lw a1, 0(a1)
	bge a7, a1, .lca_b.71
.lca_b.18: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a7, a7, a1
	addi a1, a5, 12
	lw a1, 0(a1)
	bge a7, a1, .lca_b.70
.lca_b.19: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a7, a7, a1
	addi a1, a5, 8
	lw a1, 0(a1)
	bge a7, a1, .lca_b.69
.lca_b.20: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	lw a7, 0(a1)
	lw a1, 0(a6)
	sub a6, a7, a1
	addi a1, a5, 4
	lw a1, 0(a1)
	bge a6, a1, .lca_b.68
.lca_b.21: 
	li a1, 4
	mul a1, a2, a1
	add a1, a4, a1
	li a6, 4
	mul a6, a0, a6
	add a4, a4, a6
	lw a6, 0(a1)
	lw a1, 0(a4)
	sub a4, a6, a1
	lw a1, 0(a5)
	bge a4, a1, .lca_b.67
.lca_b.22: 
	beq a2, a0, .lca_b.65
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 80
	li a4, 4
	mul a4, a0, a4
	add a4, a3, a4
	lw a4, 0(a4)
	addi a4, a4, 80
	lw a1, 0(a1)
	lw a5, 0(a4)
	bne a1, a5, .lca_b.66
	mv a1, a2
	mv a5, a0
.lca_b.23: 
	li a0, 4
	mul a0, a1, a0
	add a0, a3, a0
	lw a0, 0(a0)
	addi a0, a0, 76
	li a2, 4
	mul a2, a5, a2
	add a2, a3, a2
	lw a2, 0(a2)
	addi a2, a2, 76
	lw a0, 0(a0)
	lw a4, 0(a2)
	bne a0, a4, .lca_b.64
	mv a0, a1
	mv a4, a5
.lca_b.24: 
	li a1, 4
	mul a1, a0, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 72
	li a2, 4
	mul a2, a4, a2
	add a2, a3, a2
	lw a2, 0(a2)
	addi a2, a2, 72
	lw a1, 0(a1)
	lw a2, 0(a2)
	bne a1, a2, .lca_b.63
	mv a1, a0
	mv a2, a4
.lca_b.25: 
	li a0, 4
	mul a0, a1, a0
	add a0, a3, a0
	lw a0, 0(a0)
	addi a0, a0, 68
	li a4, 4
	mul a4, a2, a4
	add a4, a3, a4
	lw a4, 0(a4)
	addi a4, a4, 68
	lw a0, 0(a0)
	lw a4, 0(a4)
	bne a0, a4, .lca_b.62
	mv a0, a1
	mv a4, a2
.lca_b.26: 
	li a1, 4
	mul a1, a0, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 64
	li a2, 4
	mul a2, a4, a2
	add a2, a3, a2
	lw a2, 0(a2)
	addi a2, a2, 64
	lw a1, 0(a1)
	lw a2, 0(a2)
	bne a1, a2, .lca_b.61
	mv a1, a0
	mv a2, a4
.lca_b.27: 
	li a0, 4
	mul a0, a1, a0
	add a0, a3, a0
	lw a0, 0(a0)
	addi a0, a0, 60
	li a4, 4
	mul a4, a2, a4
	add a4, a3, a4
	lw a4, 0(a4)
	addi a4, a4, 60
	lw a0, 0(a0)
	lw a4, 0(a4)
	bne a0, a4, .lca_b.60
	mv a0, a1
	mv a4, a2
.lca_b.28: 
	li a1, 4
	mul a1, a0, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 56
	li a2, 4
	mul a2, a4, a2
	add a2, a3, a2
	lw a2, 0(a2)
	addi a2, a2, 56
	lw a1, 0(a1)
	lw a2, 0(a2)
	bne a1, a2, .lca_b.59
	mv a1, a0
	mv a2, a4
.lca_b.29: 
	li a0, 4
	mul a0, a1, a0
	add a0, a3, a0
	lw a0, 0(a0)
	addi a0, a0, 52
	li a4, 4
	mul a4, a2, a4
	add a4, a3, a4
	lw a4, 0(a4)
	addi a4, a4, 52
	lw a0, 0(a0)
	lw a4, 0(a4)
	bne a0, a4, .lca_b.58
	mv a0, a1
	mv a4, a2
.lca_b.30: 
	li a1, 4
	mul a1, a0, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 48
	li a2, 4
	mul a2, a4, a2
	add a2, a3, a2
	lw a2, 0(a2)
	addi a2, a2, 48
	lw a1, 0(a1)
	lw a2, 0(a2)
	bne a1, a2, .lca_b.57
	mv a1, a0
	mv a2, a4
.lca_b.31: 
	li a0, 4
	mul a0, a1, a0
	add a0, a3, a0
	lw a0, 0(a0)
	addi a0, a0, 44
	li a4, 4
	mul a4, a2, a4
	add a4, a3, a4
	lw a4, 0(a4)
	addi a4, a4, 44
	lw a0, 0(a0)
	lw a4, 0(a4)
	bne a0, a4, .lca_b.56
	mv a0, a1
	mv a4, a2
.lca_b.32: 
	li a1, 4
	mul a1, a0, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 40
	li a2, 4
	mul a2, a4, a2
	add a2, a3, a2
	lw a2, 0(a2)
	addi a2, a2, 40
	lw a1, 0(a1)
	lw a2, 0(a2)
	bne a1, a2, .lca_b.55
	mv a1, a0
	mv a2, a4
.lca_b.33: 
	li a0, 4
	mul a0, a1, a0
	add a0, a3, a0
	lw a0, 0(a0)
	addi a0, a0, 36
	li a4, 4
	mul a4, a2, a4
	add a4, a3, a4
	lw a4, 0(a4)
	addi a4, a4, 36
	lw a0, 0(a0)
	lw a4, 0(a4)
	bne a0, a4, .lca_b.54
	mv a0, a1
	mv a4, a2
.lca_b.34: 
	li a1, 4
	mul a1, a0, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 32
	li a2, 4
	mul a2, a4, a2
	add a2, a3, a2
	lw a2, 0(a2)
	addi a2, a2, 32
	lw a1, 0(a1)
	lw a2, 0(a2)
	bne a1, a2, .lca_b.53
	mv a1, a0
	mv a2, a4
.lca_b.35: 
	li a0, 4
	mul a0, a1, a0
	add a0, a3, a0
	lw a0, 0(a0)
	addi a0, a0, 28
	li a4, 4
	mul a4, a2, a4
	add a4, a3, a4
	lw a4, 0(a4)
	addi a4, a4, 28
	lw a0, 0(a0)
	lw a4, 0(a4)
	bne a0, a4, .lca_b.52
	mv a0, a1
	mv a4, a2
.lca_b.36: 
	li a1, 4
	mul a1, a0, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 24
	li a2, 4
	mul a2, a4, a2
	add a2, a3, a2
	lw a2, 0(a2)
	addi a2, a2, 24
	lw a1, 0(a1)
	lw a2, 0(a2)
	bne a1, a2, .lca_b.51
	mv a1, a0
	mv a2, a4
.lca_b.37: 
	li a0, 4
	mul a0, a1, a0
	add a0, a3, a0
	lw a0, 0(a0)
	addi a0, a0, 20
	li a4, 4
	mul a4, a2, a4
	add a4, a3, a4
	lw a4, 0(a4)
	addi a4, a4, 20
	lw a0, 0(a0)
	lw a4, 0(a4)
	bne a0, a4, .lca_b.50
	mv a0, a1
	mv a4, a2
.lca_b.38: 
	li a1, 4
	mul a1, a0, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 16
	li a2, 4
	mul a2, a4, a2
	add a2, a3, a2
	lw a2, 0(a2)
	addi a2, a2, 16
	lw a1, 0(a1)
	lw a2, 0(a2)
	bne a1, a2, .lca_b.49
	mv a1, a0
	mv a2, a4
.lca_b.39: 
	li a0, 4
	mul a0, a1, a0
	add a0, a3, a0
	lw a0, 0(a0)
	addi a0, a0, 12
	li a4, 4
	mul a4, a2, a4
	add a4, a3, a4
	lw a4, 0(a4)
	addi a4, a4, 12
	lw a0, 0(a0)
	lw a4, 0(a4)
	bne a0, a4, .lca_b.48
	mv a0, a1
	mv a4, a2
.lca_b.40: 
	li a1, 4
	mul a1, a0, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 8
	li a2, 4
	mul a2, a4, a2
	add a2, a3, a2
	lw a2, 0(a2)
	addi a2, a2, 8
	lw a1, 0(a1)
	lw a2, 0(a2)
	bne a1, a2, .lca_b.47
	mv a1, a0
	mv a2, a4
.lca_b.41: 
	li a0, 4
	mul a0, a1, a0
	add a0, a3, a0
	lw a0, 0(a0)
	addi a0, a0, 4
	li a4, 4
	mul a4, a2, a4
	add a4, a3, a4
	lw a4, 0(a4)
	addi a4, a4, 4
	lw a0, 0(a0)
	lw a4, 0(a4)
	bne a0, a4, .lca_b.46
	mv a0, a1
	mv a4, a2
.lca_b.42: 
	li a1, 4
	mul a1, a0, a1
	add a1, a3, a1
	lw a1, 0(a1)
	li a2, 4
	mul a2, a4, a2
	add a2, a3, a2
	lw a2, 0(a2)
	lw a1, 0(a1)
	lw a2, 0(a2)
	bne a1, a2, .lca_b.45
	mv a1, a0
.lca_b.43: 
	li a0, 4
	mul a0, a1, a0
	add a0, a3, a0
	lw a0, 0(a0)
	lw a2, 0(a0)
.lca_b.44: 
	mv a0, a2
	ret
.lca_b.45: 
	j .lca_b.43
.lca_b.46: 
	j .lca_b.42
.lca_b.47: 
	j .lca_b.41
.lca_b.48: 
	j .lca_b.40
.lca_b.49: 
	j .lca_b.39
.lca_b.50: 
	j .lca_b.38
.lca_b.51: 
	j .lca_b.37
.lca_b.52: 
	j .lca_b.36
.lca_b.53: 
	j .lca_b.35
.lca_b.54: 
	j .lca_b.34
.lca_b.55: 
	j .lca_b.33
.lca_b.56: 
	j .lca_b.32
.lca_b.57: 
	j .lca_b.31
.lca_b.58: 
	j .lca_b.30
.lca_b.59: 
	j .lca_b.29
.lca_b.60: 
	j .lca_b.28
.lca_b.61: 
	j .lca_b.27
.lca_b.62: 
	j .lca_b.26
.lca_b.63: 
	j .lca_b.25
.lca_b.64: 
	j .lca_b.24
.lca_b.65: 
	j .lca_b.44
.lca_b.66: 
	j .lca_b.23
.lca_b.67: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	lw a2, 0(a1)
	j .lca_b.22
.lca_b.68: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 4
	lw a2, 0(a1)
	j .lca_b.21
.lca_b.69: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 8
	lw a2, 0(a1)
	j .lca_b.20
.lca_b.70: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 12
	lw a2, 0(a1)
	j .lca_b.19
.lca_b.71: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 16
	lw a2, 0(a1)
	j .lca_b.18
.lca_b.72: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 20
	lw a2, 0(a1)
	j .lca_b.17
.lca_b.73: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 24
	lw a2, 0(a1)
	j .lca_b.16
.lca_b.74: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 28
	lw a2, 0(a1)
	j .lca_b.15
.lca_b.75: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 32
	lw a2, 0(a1)
	j .lca_b.14
.lca_b.76: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 36
	lw a2, 0(a1)
	j .lca_b.13
.lca_b.77: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 40
	lw a2, 0(a1)
	j .lca_b.12
.lca_b.78: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 44
	lw a2, 0(a1)
	j .lca_b.11
.lca_b.79: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 48
	lw a2, 0(a1)
	j .lca_b.10
.lca_b.80: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 52
	lw a2, 0(a1)
	j .lca_b.9
.lca_b.81: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 56
	lw a2, 0(a1)
	j .lca_b.8
.lca_b.82: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 60
	lw a2, 0(a1)
	j .lca_b.7
.lca_b.83: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 64
	lw a2, 0(a1)
	j .lca_b.6
.lca_b.84: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 68
	lw a2, 0(a1)
	j .lca_b.5
.lca_b.85: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 72
	lw a2, 0(a1)
	j .lca_b.4
.lca_b.86: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 76
	lw a2, 0(a1)
	j .lca_b.3
.lca_b.87: 
	li a1, 4
	mul a1, a2, a1
	add a1, a3, a1
	lw a1, 0(a1)
	addi a1, a1, 80
	lw a2, 0(a1)
	j .lca_b.2
.lca_b.88: 
	mv a2, a1
	j .lca_b.1

	.globl	main
	.p2align	1
	.type	main,@function
main:
.main_b.0: 
	addi sp, sp, -64
	sw s0, 16(sp)
	sw s1, 20(sp)
	sw s2, 24(sp)
	sw s3, 28(sp)
	sw s4, 32(sp)
	sw s5, 36(sp)
	sw s6, 40(sp)
	sw s7, 44(sp)
	sw s8, 48(sp)
	sw s9, 52(sp)
	sw s10, 56(sp)
	sw s11, 60(sp)
	sw ra, 12(sp)
	call _gbl_getInt
	mv s0, a0
	call _gbl_getInt
	mv s1, a0
	call _gbl_getInt
	lui a1, %hi(n)
	sw s0, %lo(n)(a1)
	lui a1, %hi(m)
	sw s1, %lo(m)(a1)
	lui a1, %hi(root)
	sw a0, %lo(root)(a1)
	lui a0, %hi(total)
	sw zero, %lo(total)(a0)
	li a1, 20
	lui a0, %hi(MAX)
	sw a1, %lo(MAX)(a0)
	call init
	li s2, 1
.main_b.1: 
	lw a0, n
	addi a0, a0, -1
	ble s2, a0, .main_b.42
	lw s10, root
	lw a1, depth
	li a0, 4
	mul a0, s10, a0
	add a0, a1, a0
	lw a1, 0(a1)
	addi a1, a1, 1
	sw a1, 0(a0)
	lw a4, f
	li a0, 4
	mul a0, s10, a0
	add a0, a4, a0
	lw a3, 0(a0)
	sw zero, 0(a3)
	li a2, 1
.main_b.2: 
	li a0, 20
	ble a2, a0, .main_b.41
	lw a1, first
	li a0, 4
	mul a0, s10, a0
	add a0, a1, a0
	lw a0, 0(a0)
	mv s9, a0
.main_b.3: 
	bne s9, zero, .main_b.8
	li s2, 1
.main_b.4: 
	lw a0, m
	ble s2, a0, .main_b.7
	li s0, 1
.main_b.5: 
	lw a0, m
	ble s0, a0, .main_b.6
	mv a0, zero
	lw s0, 16(sp)
	lw s1, 20(sp)
	lw s2, 24(sp)
	lw s3, 28(sp)
	lw s4, 32(sp)
	lw s5, 36(sp)
	lw s6, 40(sp)
	lw s7, 44(sp)
	lw s8, 48(sp)
	lw s9, 52(sp)
	lw s10, 56(sp)
	lw s11, 60(sp)
	lw ra, 12(sp)
	addi sp, sp, 64
	ret
.main_b.6: 
	lw a1, ans
	li a0, 4
	mul a0, s0, a0
	add a0, a1, a0
	lw a0, 0(a0)
	call _gbl_toString
	call _gbl_println
	addi s0, s0, 1
	j .main_b.5
.main_b.7: 
	call _gbl_getInt
	mv s0, a0
	call _gbl_getInt
	mv a1, a0
	lw a2, ans
	li a0, 4
	mul a0, s2, a0
	add s1, a2, a0
	mv a0, s0
	call lca
	sw a0, 0(s1)
	addi s2, s2, 1
	j .main_b.4
.main_b.8: 
	lw a1, edge
	li a0, 4
	mul a0, s9, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 4
	lw s8, 0(a0)
	bne s8, zero, .main_b.10
.main_b.9: 
	lw a1, edge
	li a0, 4
	mul a0, s9, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 8
	lw a0, 0(a0)
	mv s9, a0
	j .main_b.3
.main_b.10: 
	lw a2, depth
	li a0, 4
	mul a0, s8, a0
	add a0, a2, a0
	li a1, 4
	mul a1, s10, a1
	add a1, a2, a1
	lw a1, 0(a1)
	addi a1, a1, 1
	sw a1, 0(a0)
	lw a4, f
	li a0, 4
	mul a0, s8, a0
	add a0, a4, a0
	lw a3, 0(a0)
	sw s10, 0(a3)
	li a2, 1
.main_b.11: 
	li a0, 20
	ble a2, a0, .main_b.40
	lw a1, first
	li a0, 4
	mul a0, s8, a0
	add a0, a1, a0
	lw a0, 0(a0)
	mv s7, a0
.main_b.12: 
	bne s7, zero, .main_b.13
	j .main_b.9
.main_b.13: 
	lw a1, edge
	li a0, 4
	mul a0, s7, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 4
	lw s6, 0(a0)
	bne s6, s10, .main_b.15
.main_b.14: 
	lw a1, edge
	li a0, 4
	mul a0, s7, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 8
	lw a0, 0(a0)
	mv s7, a0
	j .main_b.12
.main_b.15: 
	lw a2, depth
	li a0, 4
	mul a0, s6, a0
	add a0, a2, a0
	li a1, 4
	mul a1, s8, a1
	add a1, a2, a1
	lw a1, 0(a1)
	addi a1, a1, 1
	sw a1, 0(a0)
	lw a4, f
	li a0, 4
	mul a0, s6, a0
	add a0, a4, a0
	lw a3, 0(a0)
	sw s8, 0(a3)
	li a2, 1
.main_b.16: 
	li a0, 20
	ble a2, a0, .main_b.39
	lw a1, first
	li a0, 4
	mul a0, s6, a0
	add a0, a1, a0
	lw a0, 0(a0)
	mv s5, a0
.main_b.17: 
	bne s5, zero, .main_b.18
	j .main_b.14
.main_b.18: 
	lw a1, edge
	li a0, 4
	mul a0, s5, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 4
	lw s4, 0(a0)
	bne s4, s8, .main_b.20
.main_b.19: 
	lw a1, edge
	li a0, 4
	mul a0, s5, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 8
	lw a0, 0(a0)
	mv s5, a0
	j .main_b.17
.main_b.20: 
	lw a2, depth
	li a0, 4
	mul a0, s4, a0
	add a0, a2, a0
	li a1, 4
	mul a1, s6, a1
	add a1, a2, a1
	lw a1, 0(a1)
	addi a1, a1, 1
	sw a1, 0(a0)
	lw a4, f
	li a0, 4
	mul a0, s4, a0
	add a0, a4, a0
	lw a3, 0(a0)
	sw s6, 0(a3)
	li a2, 1
.main_b.21: 
	li a0, 20
	ble a2, a0, .main_b.38
	lw a1, first
	li a0, 4
	mul a0, s4, a0
	add a0, a1, a0
	lw a0, 0(a0)
	mv s3, a0
.main_b.22: 
	bne s3, zero, .main_b.23
	j .main_b.19
.main_b.23: 
	lw a1, edge
	li a0, 4
	mul a0, s3, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 4
	lw s2, 0(a0)
	bne s2, s6, .main_b.25
.main_b.24: 
	lw a1, edge
	li a0, 4
	mul a0, s3, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 8
	lw a0, 0(a0)
	mv s3, a0
	j .main_b.22
.main_b.25: 
	lw a2, depth
	li a0, 4
	mul a0, s2, a0
	add a0, a2, a0
	li a1, 4
	mul a1, s4, a1
	add a1, a2, a1
	lw a1, 0(a1)
	addi a1, a1, 1
	sw a1, 0(a0)
	lw a4, f
	li a0, 4
	mul a0, s2, a0
	add a0, a4, a0
	lw a3, 0(a0)
	sw s4, 0(a3)
	li a2, 1
.main_b.26: 
	li a0, 20
	ble a2, a0, .main_b.37
	lw a1, first
	li a0, 4
	mul a0, s2, a0
	add a0, a1, a0
	lw a0, 0(a0)
	mv s1, a0
.main_b.27: 
	bne s1, zero, .main_b.28
	j .main_b.24
.main_b.28: 
	lw a1, edge
	li a0, 4
	mul a0, s1, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 4
	lw s11, 0(a0)
	bne s11, s4, .main_b.30
.main_b.29: 
	lw a1, edge
	li a0, 4
	mul a0, s1, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 8
	lw a0, 0(a0)
	mv s1, a0
	j .main_b.27
.main_b.30: 
	lw a2, depth
	li a0, 4
	mul a0, s11, a0
	add a0, a2, a0
	li a1, 4
	mul a1, s2, a1
	add a1, a2, a1
	lw a1, 0(a1)
	addi a1, a1, 1
	sw a1, 0(a0)
	lw a4, f
	li a0, 4
	mul a0, s11, a0
	add a0, a4, a0
	lw a3, 0(a0)
	sw s2, 0(a3)
	li a2, 1
.main_b.31: 
	li a0, 20
	ble a2, a0, .main_b.36
	lw a1, first
	li a0, 4
	mul a0, s11, a0
	add a0, a1, a0
	lw a0, 0(a0)
	mv s0, a0
.main_b.32: 
	bne s0, zero, .main_b.33
	j .main_b.29
.main_b.33: 
	lw a1, edge
	li a0, 4
	mul a0, s0, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 4
	lw a0, 0(a0)
	bne a0, s2, .main_b.35
.main_b.34: 
	lw a1, edge
	li a0, 4
	mul a0, s0, a0
	add a0, a1, a0
	lw a0, 0(a0)
	addi a0, a0, 8
	lw a0, 0(a0)
	mv s0, a0
	j .main_b.32
.main_b.35: 
	mv a1, s11
	call dfs
	j .main_b.34
.main_b.36: 
	li a0, 4
	mul a0, a2, a0
	add a0, a3, a0
	addi a5, a2, -1
	li a1, 4
	mul a1, a5, a1
	add a1, a3, a1
	lw a6, 0(a1)
	li a1, 4
	mul a1, a6, a1
	add a1, a4, a1
	lw a6, 0(a1)
	li a1, 4
	mul a1, a5, a1
	add a1, a6, a1
	lw a1, 0(a1)
	sw a1, 0(a0)
	addi a2, a2, 1
	j .main_b.31
.main_b.37: 
	li a0, 4
	mul a0, a2, a0
	add a0, a3, a0
	addi a6, a2, -1
	li a1, 4
	mul a1, a6, a1
	add a1, a3, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a5, a1
	add a1, a4, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a6, a1
	add a1, a5, a1
	lw a1, 0(a1)
	sw a1, 0(a0)
	addi a2, a2, 1
	j .main_b.26
.main_b.38: 
	li a0, 4
	mul a0, a2, a0
	add a0, a3, a0
	addi a6, a2, -1
	li a1, 4
	mul a1, a6, a1
	add a1, a3, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a5, a1
	add a1, a4, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a6, a1
	add a1, a5, a1
	lw a1, 0(a1)
	sw a1, 0(a0)
	addi a2, a2, 1
	j .main_b.21
.main_b.39: 
	li a0, 4
	mul a0, a2, a0
	add a0, a3, a0
	addi a6, a2, -1
	li a1, 4
	mul a1, a6, a1
	add a1, a3, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a5, a1
	add a1, a4, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a6, a1
	add a1, a5, a1
	lw a1, 0(a1)
	sw a1, 0(a0)
	addi a2, a2, 1
	j .main_b.16
.main_b.40: 
	li a0, 4
	mul a0, a2, a0
	add a0, a3, a0
	addi a6, a2, -1
	li a1, 4
	mul a1, a6, a1
	add a1, a3, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a5, a1
	add a1, a4, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a6, a1
	add a1, a5, a1
	lw a1, 0(a1)
	sw a1, 0(a0)
	addi a2, a2, 1
	j .main_b.11
.main_b.41: 
	li a0, 4
	mul a0, a2, a0
	add a0, a3, a0
	addi a6, a2, -1
	li a1, 4
	mul a1, a6, a1
	add a1, a3, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a5, a1
	add a1, a4, a1
	lw a5, 0(a1)
	li a1, 4
	mul a1, a6, a1
	add a1, a5, a1
	lw a1, 0(a1)
	sw a1, 0(a0)
	addi a2, a2, 1
	j .main_b.2
.main_b.42: 
	call _gbl_getInt
	mv s0, a0
	call _gbl_getInt
	mv s1, a0
	mv a0, s0
	mv a1, s1
	call addedge
	mv a0, s1
	mv a1, s0
	call addedge
	addi s2, s2, 1
	j .main_b.1

	.type	n,@object
	.section	.data
	.globl	n
	.p2align	2
n:
	.zero	4
	.size	n, 4

	.type	first,@object
	.section	.data
	.globl	first
	.p2align	2
first:
	.zero	4
	.size	first, 4

	.type	depth,@object
	.section	.data
	.globl	depth
	.p2align	2
depth:
	.zero	4
	.size	depth, 4

	.type	m,@object
	.section	.data
	.globl	m
	.p2align	2
m:
	.zero	4
	.size	m, 4

	.type	ans,@object
	.section	.data
	.globl	ans
	.p2align	2
ans:
	.zero	4
	.size	ans, 4

	.type	f,@object
	.section	.data
	.globl	f
	.p2align	2
f:
	.zero	4
	.size	f, 4

	.type	pow,@object
	.section	.data
	.globl	pow
	.p2align	2
pow:
	.zero	4
	.size	pow, 4

	.type	edge,@object
	.section	.data
	.globl	edge
	.p2align	2
edge:
	.zero	4
	.size	edge, 4

	.type	total,@object
	.section	.data
	.globl	total
	.p2align	2
total:
	.zero	4
	.size	total, 4

	.type	root,@object
	.section	.data
	.globl	root
	.p2align	2
root:
	.zero	4
	.size	root, 4

	.type	MAX,@object
	.section	.data
	.globl	MAX
	.p2align	2
MAX:
	.zero	4
	.size	MAX, 4

